package com.supafit.common.security;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.supafit.common.exceptions.InvalidRequestException;
import com.supafit.common.notification.EmailNotification;
import com.supafit.common.util.RandomNumberUtil;
import com.supafit.dao.coach.CoachManager;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.coach.model.CoachType;
import com.supafit.dao.user.UserManager;
import com.supafit.dao.user.model.User;

@Component
@Scope("prototype")
public class AuthorizationCodeServices {

	private final String CLIENT_ID = "supafit-mobile-app";
	private final String CLIENT_SECRET = "SsUbJJio22nH3rgFf32eRFEF43dedc2wfc2ef_RF34wsdxSXQSCX34RDSdcsd";
	private final String OAUTH_ENDPOINT = "http://localhost:8181/supafit-api/oauth/token?scope=read,write,trust&";
	private UserManager userManager;
	private EmailNotification emailNotification;
	private CoachManager coachManager;
	private final String TAG = this.getClass().getName();
	Logger logger = Logger.getLogger(AuthorizationCodeServices.class.getName());

	@Autowired
	public void setCoachManager(CoachManager coachManager) {
		this.coachManager = coachManager;
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public SupafitUserLoginResponse socialLogin(String authorizationServerType, String authorizationCode,
			String clientCredentials, String origin) throws Exception {

		SupafitUserLoginResponse socialLoginResponse = null;
		if ("google".equalsIgnoreCase(authorizationServerType)) {
			validateClient(clientCredentials);
			socialLoginResponse = new SupafitUserLoginResponse();
			SupafitUserCredentials supafitUser = new SupafitUserCredentials();
			String tokenJSON = null;
			AuthCodeGoogleUtil authUtil = new AuthCodeGoogleUtil();
			Credential credentials = AuthCodeGoogleUtil.exchangeCode(authorizationCode);
			Userinfoplus userInfo = authUtil.getUserInfo(credentials);
			if (userInfo == null) {
				logger.log(Level.FATAL, TAG + " " + "Invalid Authorization code provided. Code: " + authorizationCode);
				throw new InvalidRequestException("Invalid Authorization code provided");
			}

			supafitUser.setId(userInfo.getId());
			supafitUser.setEmail(userInfo.getEmail());
			supafitUser.setGivenName(userInfo.getGivenName());
			supafitUser.setFamilyName(userInfo.getFamilyName());
			supafitUser.setPicture(userInfo.getPicture());

			User user = new User();
			CoachType userType = new CoachType();
			userType.setId(1);
			// user.setUserType(userType);
			user.setUserId(userInfo.getId());
			user.setName(userInfo.getGivenName() + " " + userInfo.getFamilyName());
			user.setEmail(userInfo.getEmail());
			String randomPassword = String.valueOf(RandomNumberUtil.getRandomNumber());
			String encryptedPassword = PasswordEncryptionUtil.encryptPassword(randomPassword);
			user.setPassword(encryptedPassword);
			user.setImageURL(userInfo.getPicture());
			User existingUser = userManager.getUserByUserId(userInfo.getEmail());
			if (existingUser == null) {
				logger.log(Level.INFO, TAG + " " 
								+ "User is being signed up. email: " 
								+ userInfo.getEmail());
				existingUser = userManager.createUser(user);
				user.setPassword(randomPassword);
				emailNotification = new EmailNotification(user, null);
				emailNotification.sendCreateAccountMail();
				tokenJSON = getResourceOAuthToken(userInfo.getEmail(), encryptedPassword);
			} else {
				logger.log(Level.INFO,
						TAG + " "
								+ "User with this email id already exists. Returning the token for this user. email: "
								+ userInfo.getEmail());
				tokenJSON = getResourceOAuthToken(existingUser.getEmail(), existingUser.getPassword());
			}
			socialLoginResponse.setUser(existingUser);
			socialLoginResponse.setToken(tokenJSON);
		} else if ("facebook".equalsIgnoreCase(authorizationServerType)) {
			String tokenJSON = null;
			socialLoginResponse = new SupafitUserLoginResponse();
			AuthCodeFacebookUtil facebookAuthUtil = new AuthCodeFacebookUtil();
			String accessToken;
			if (origin != null && "web".equalsIgnoreCase(origin)) {
				accessToken = facebookAuthUtil.getAccessToken(authorizationCode);
			} else {
				validateClient(clientCredentials);
				accessToken = "access_token=" + authorizationCode;
			}
			String fbGraph = facebookAuthUtil.getFBGraph(accessToken);
			Map<String, String> resultMap = facebookAuthUtil.getGraphData(fbGraph);

			String firstName = resultMap.get("first_name");
			String lastName = resultMap.get("last_name");
			String name = firstName + " " + lastName;
			String userId = resultMap.get("id");
			String email = resultMap.get("email");
			String gender = resultMap.get("gender");

			User user = new User();
			CoachType userType = new CoachType();
			userType.setId(1);
			user.setUserId(email);
			user.setName(name);
			user.setEmail(email);
			String randomPassword = String.valueOf(RandomNumberUtil.getRandomNumber());
			String encryptedPassword = PasswordEncryptionUtil.encryptPassword(randomPassword);
			user.setPassword(encryptedPassword);
			user.setImageURL(resultMap.get("imageURL"));
			user.setGender(gender);
			User existingUser = userManager.getUserByUserId(email);
			if (existingUser == null) {
				logger.log(Level.INFO, TAG + " " 
						+ "User is being signed up. email: " 
						+ user.getEmail());
				existingUser = userManager.createUser(user);
				user.setPassword(randomPassword);
				emailNotification = new EmailNotification(user, null);
				emailNotification.sendCreateAccountMail();
			} else {
				logger.log(Level.INFO,
						TAG + " "
								+ "User with this email id already exists. Returning the token for this user. email: "
								+ user.getEmail());
				encryptedPassword = existingUser.getPassword();
			}
			socialLoginResponse.setUser(existingUser);
			tokenJSON = getResourceOAuthToken(email, encryptedPassword);
			socialLoginResponse.setToken(tokenJSON);

		}
		return socialLoginResponse;
	}

	public SupafitUserLoginResponse socialLogin(String authorizationServerType, String authorizationCode)
			throws Exception {

		// validateClient(clientCredentials);
		SupafitUserLoginResponse socialLoginResponse = null;
		if ("google".equalsIgnoreCase(authorizationServerType)) {
			socialLoginResponse = new SupafitUserLoginResponse();
			SupafitUserCredentials supafitUser = new SupafitUserCredentials();
			String tokenJSON = null;
			AuthCodeGoogleUtil authUtil = new AuthCodeGoogleUtil();
			Credential credentials = AuthCodeGoogleUtil.exchangeCode(authorizationCode);
			Userinfoplus userInfo = authUtil.getUserInfo(credentials);
			if (userInfo == null)
				throw new InvalidRequestException("Invalid Authorization code provided");

			supafitUser.setId(userInfo.getId());
			supafitUser.setEmail(userInfo.getEmail());
			supafitUser.setGivenName(userInfo.getGivenName());
			supafitUser.setFamilyName(userInfo.getFamilyName());
			supafitUser.setPicture(userInfo.getPicture());

			User user = new User();
			CoachType userType = new CoachType();
			userType.setId(1);
			// user.setUserType(userType);
			user.setUserId(userInfo.getId());
			user.setName(userInfo.getGivenName() + " " + userInfo.getFamilyName());
			user.setEmail(userInfo.getEmail());
			String randomPassword = String.valueOf(RandomNumberUtil.getRandomNumber());
			String encryptedPassword = PasswordEncryptionUtil.encryptPassword(randomPassword);
			user.setPassword(encryptedPassword);
			user.setImageURL(userInfo.getPicture());
			User existingUser = userManager.getUserByUserId(userInfo.getEmail());
			if (existingUser == null) {
				logger.log(Level.INFO, TAG + " " 
						+ "User is being signed up. email: " 
						+ userInfo.getEmail());
				existingUser = userManager.createUser(user);
				user.setPassword(randomPassword);
				emailNotification = new EmailNotification(user, null);
				emailNotification.sendCreateAccountMail();
				tokenJSON = getResourceOAuthToken(userInfo.getEmail(), encryptedPassword);
			} else {
				logger.log(Level.INFO,
						TAG + " "
								+ "User with this email id already exists. Returning the token for this user. email: "
								+ userInfo.getEmail());
				tokenJSON = getResourceOAuthToken(existingUser.getEmail(), existingUser.getPassword());
			}
			socialLoginResponse.setUser(existingUser);
			socialLoginResponse.setToken(tokenJSON);
		} else if ("facebook".equalsIgnoreCase(authorizationServerType)) {
			String tokenJSON = null;
			socialLoginResponse = new SupafitUserLoginResponse();
			AuthCodeFacebookUtil facebookAuthUtil = new AuthCodeFacebookUtil();
			String accessToken = facebookAuthUtil.getAccessToken(authorizationCode);
			String fbGraph = facebookAuthUtil.getFBGraph(accessToken);
			Map<String, String> resultMap = facebookAuthUtil.getGraphData(fbGraph);

			String firstName = resultMap.get("first_name");
			String lastName = resultMap.get("last_name");
			String name = firstName + " " + lastName;
			String userId = resultMap.get("id");
			String email = resultMap.get("email");
			String gender = resultMap.get("gender");

			User user = new User();
			CoachType userType = new CoachType();
			userType.setId(1);
			user.setUserId(email);
			user.setName(name);
			user.setEmail(email);
			String randomPassword = String.valueOf(RandomNumberUtil.getRandomNumber());
			String encryptedPassword = PasswordEncryptionUtil.encryptPassword(randomPassword);
			user.setPassword(encryptedPassword);
			user.setImageURL(resultMap.get("imageURL"));
			user.setGender(gender);
			User existingUser = userManager.getUserByUserId(email);
			if (existingUser == null) {
				logger.log(Level.INFO, TAG + " " 
						+ "User is being signed up. email: " 
						+ user.getEmail());
				existingUser = userManager.createUser(user);
				user.setPassword(randomPassword);
				emailNotification = new EmailNotification(user, null);
				emailNotification.sendCreateAccountMail();
			} else {
				logger.log(Level.INFO,
						TAG + " "
								+ "User with this email id already exists. Returning the token for this user. email: "
								+ user.getEmail());
				encryptedPassword = existingUser.getPassword();
			}
			socialLoginResponse.setUser(existingUser);
			tokenJSON = getResourceOAuthToken(email, encryptedPassword);
			socialLoginResponse.setToken(tokenJSON);

		}
		return socialLoginResponse;
	}

	public void validateClient(String clientCredentials) throws InvalidRequestException {
		UserLoginDataDTO loginData = getLoginData(clientCredentials);
		if (!validateClient(loginData.getClientId(), loginData.getClientSecret()))
			throw new InvalidRequestException("Invalid Client Credentials!");
	}

	public String getResourceOAuthToken(String userName, String password)
			throws ClientProtocolException, IOException, URISyntaxException {

		String url = OAUTH_ENDPOINT + "grant_type=password" + "&client_id=" + CLIENT_ID + "&client_secret="
				+ CLIENT_SECRET + "&username=" + userName + "&password=" + password;
		String json_string = null;
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		URI myUri = new URI(url);
		HttpPost post = new HttpPost(myUri);
		try {
			response = httpclient.execute(post);
			json_string = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
		}
		return json_string;
	}

	public User validateUserLogIn(String userCredentials, UserLoginDataDTO loginData) throws InvalidRequestException {
		boolean clientValidation = validateClient(loginData.getClientId(), loginData.getClientSecret());
		User userValidation = validateUser(loginData.getUserName(), loginData.getPassword());
		if (!clientValidation || userValidation == null)
			throw new InvalidRequestException("Invalid Credentials!");
		return userValidation;
	}

	public Coach validateCoachLogIn(String userCredentials, UserLoginDataDTO loginData) throws InvalidRequestException {
		boolean clientValidation = validateClient(loginData.getClientId(), loginData.getClientSecret());
		Coach userValidation = validateCoach(loginData.getUserName(), loginData.getPassword());
		if (!clientValidation || userValidation == null)
			throw new InvalidRequestException("Invalid Credentials!");
		return userValidation;
	}

	private User validateUser(String userName, String password) {
		return userManager.getUserByUserNameAndPassword(userName, password);
	}

	private Coach validateCoach(String userName, String password) {
		return coachManager.getCoachByUserNameAndPassword(userName, password);
	}

	private boolean validateClient(String clientId, String clientSecret) {
		if (CLIENT_ID.equals(clientId) && CLIENT_SECRET.equals(clientSecret))
			return true;
		return false;
	}

	private UserLoginDataDTO getLoginData(String userCredentials) {
		UserLoginDataDTO loginData = new UserLoginDataDTO();
		List<String> commaSeparatedValues = Arrays.asList(userCredentials.split("\\s*,\\s*"));
		String clientId = null;
		String clientSecret = null;
		String userName = null;
		String password = null;
		for (int i = 0; i < commaSeparatedValues.size(); i++) {
			String commaSeparatedValue = commaSeparatedValues.get(i);
			if (i == 0) {
				clientId = commaSeparatedValue.replace("client_id", "").replace(":", "");
			} else if (i == 1) {
				clientSecret = commaSeparatedValue.replace("client_secret", "").replace(":", "");
			} else if (i == 2) {
				userName = commaSeparatedValue.replace("user_name", "").replace(":", "");
			} else {
				password = commaSeparatedValue.replace("password", "").replace(":", "");
			}
		}
		loginData.setClientId(clientId);
		loginData.setClientSecret(clientSecret);
		loginData.setPassword(password);
		loginData.setUserName(userName);
		return loginData;
	}

	public SupafitUserLoginResponse supafitLogIn(String userCredentials)
			throws InvalidRequestException, ClientProtocolException, IOException, URISyntaxException {

		SupafitUserLoginResponse loginResponse = new SupafitUserLoginResponse();
		UserLoginDataDTO loginData = getLoginData(userCredentials);
		User user = validateUserLogIn(userCredentials, loginData);
		// validateClient(userCredentials);
		String resourceOauthToken = getResourceOAuthToken(loginData.getUserName(), loginData.getPassword());
		loginResponse.setToken(resourceOauthToken);
		loginResponse.setUser(user);
		return loginResponse;
	}

	public SupafitCoachLoginResponse supafitCoachLogIn(String userCredentials)
			throws InvalidRequestException, ClientProtocolException, IOException, URISyntaxException {
		SupafitCoachLoginResponse loginResponse = new SupafitCoachLoginResponse();
		UserLoginDataDTO loginData = getLoginData(userCredentials);
		Coach coach = validateCoachLogIn(userCredentials, loginData);
		String resourceOauthToken = getResourceOAuthToken(loginData.getUserName(), loginData.getPassword());
		loginResponse.setToken(resourceOauthToken);
		loginResponse.setCoach(coach);
		return loginResponse;
	}
}
