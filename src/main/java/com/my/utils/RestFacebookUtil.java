package com.my.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;

import lombok.Data;

@Component
public class RestFacebookUtil {
	public static String FACEBOOK_APP_ID = "1548757658620070";
	public static String FACEBOOK_APP_SECRET = "00fbfdc54008e8f7c047d588cc0fc196";
	public static String FACEBOOK_REDIRECT_URL = "http://localhost:8080/dang-nhap-bang-facebook";
	public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

	public String getToken(final String code) throws ClientProtocolException, IOException {
		String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET,
				FACEBOOK_REDIRECT_URL, code);
		String response = Request.Get(link).execute().returnContent().asString();
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode node = mapper.readTree(response).get("access_token");
//		return node.textValue();
		FacebookAccess fbacc = new Gson().fromJson(response, FacebookAccess.class);
		return fbacc.getAccess_token();
	}

	public com.restfb.types.User getUserInfo(final String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
		return facebookClient.fetchObject("me", com.restfb.types.User.class,
				Parameter.with("fields", "id,name,email,link,gender,location,hometown"));
	}

	public UserDetails buildUser(com.restfb.types.User user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails userDetail = new User(user.getId(), "", enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		return userDetail;
	}
	
	@Data
	private class FacebookAccess {
		private String access_token;
		private String token_type;
		private String expires_inexpires_in;
	}

	// tạo randompass

}
