package com.supafit.common.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthCodeFacebookUtil {
	private final String GRAPHURL = "http://graph.facebook.com/";
	public static final String FB_APP_ID = "wefw";
	public static final String FB_APP_SECRET = "fdsc";
//	public static final String REDIRECT_URI = "http://localhost/";
	public static final String REDIRECT_URI = "http://54.200.113.97:8080/supafit-api/signup/social/redirect?oauthServer=facebook";
//	public static final String REDIRECT_URI = "http://localhost:8080/supafit-api/signup/social/redirect?oauthServer=facebook";

//	static String accessToken = "";

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ AuthCodeFacebookUtil.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(AuthCodeFacebookUtil.REDIRECT_URI, "UTF-8")
					+ "&scope=email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	/*public String getFBGraphUrl(String code) throws URIException {
		String fbGraphUrl = "";
		fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + AuthCodeFacebookUtil.FB_APP_ID + "&redirect_uri="
				+ URIUtil.encodeQuery(AuthCodeFacebookUtil.REDIRECT_URI)
				+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		return fbGraphUrl;
	}*/
	
	public String getFBGraphUrl(String code) throws URIException {
		String fbGraphUrl = "";
		fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + AuthCodeFacebookUtil.FB_APP_ID + "&redirect_uri="
				+ URIUtil.encodeQuery(AuthCodeFacebookUtil.REDIRECT_URI)
				+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		return fbGraphUrl;
	}

	public String getAccessToken(String code, String accessToken) throws URIException {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		return accessToken;
	}
	
	/*public AuthCodeFacebookUtil(String authorizationCode) throws URIException {
		String accessTokenWithExpiry = getAccessToken(authorizationCode);
		String[] tokenArray = accessTokenWithExpiry.split("&");
		accessToken = tokenArray[0];
	}*/
	
	public String getAccessToken(String authorizationCode) throws URIException {
		String accessTokenWithExpiry = getAccessToken(authorizationCode, "");
		String[] tokenArray = accessTokenWithExpiry.split("&");
		String accessToken = tokenArray[0];
		return accessToken;
	}

	public String getFBGraph(String accessToken) {
		String graph = null;
		try {

			String accessTokenURL = "https://graph.facebook.com/me?fields=id,email,first_name,last_name,gender&"
					+ accessToken;
			URL u = new URL(URIUtil.encodeQuery(accessTokenURL));
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public Map<String, String> getGraphData(String fbGraph) {
		Map<String, String> fbProfile = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(fbGraph);
			fbProfile.put("id", json.getString("id"));
			if (json.has("first_name"))
				fbProfile.put("first_name", json.getString("first_name"));
			if (json.has("last_name"))
				fbProfile.put("last_name", json.getString("last_name"));
			if (json.has("email"))
				fbProfile.put("email", json.getString("email"));
			if (json.has("gender"))
				fbProfile.put("gender", json.getString("gender"));
			String imageURL = GRAPHURL + fbProfile.get("id") + "/picture";
			fbProfile.put("imageURL", imageURL);

		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}
}