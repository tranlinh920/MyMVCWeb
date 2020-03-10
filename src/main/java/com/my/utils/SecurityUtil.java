package com.my.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

	public static UserDetails getUserDetails() {
		try {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			Authentication auth = securityContext.getAuthentication();
			Object obj = auth.getPrincipal();
			return (UserDetails) obj;
		} catch (Exception e) {
			return null;
		}
	}
}
