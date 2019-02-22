package com.supafit.controller.signin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.supafit.bo.user.UserService;
import com.supafit.common.security.SupafitCoachLoginResponse;
import com.supafit.common.security.SupafitUserLoginResponse;
import com.supafit.model.question.QuestionsDTO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/signin")
@Api(value="signin", description="Operations pertaining to signing in and out to/from Supafit")
@CrossOrigin
public class SupafitSigninController {

	private UserService userService;
    private TokenStore tokenStore;
    @Autowired
	public void setTokenStore(TokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "User Login to supafit", notes = "User Login to supafit", response = SupafitUserLoginResponse.class)
	public SupafitUserLoginResponse userSignIn(@RequestHeader("User_Credentials") String userCredentials)
			throws Exception {
		return userService.supafitLogIn(userCredentials);
	} 
	
	@RequestMapping(value = "/coach", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Login to supafit", notes = "Login to supafit", response = SupafitCoachLoginResponse.class)
	public SupafitCoachLoginResponse coachSignIn(@RequestHeader("User_Credentials") String userCredentials)
			throws Exception {
		return userService.coachSignIn(userCredentials);
	} 
	
	@RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Void> logout(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			String tokenValue = authHeader.replace("Bearer", "").trim();
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
			tokenStore.removeAccessToken(accessToken);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
