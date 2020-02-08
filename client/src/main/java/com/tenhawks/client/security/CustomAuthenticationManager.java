package com.tenhawks.client.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

public class CustomAuthenticationManager implements AuthenticationManager {
	
	private CustomAuthenticationProvider customAuthenticationProvider;

		
	public CustomAuthenticationManager(CustomAuthenticationProvider customAuthenticationProvider) {
		this.customAuthenticationProvider = customAuthenticationProvider;
	
	}

	/**
	 * <p>
	 *   Authenticates current authentication.
	 * </p>
	 *
	 * @param authentication
	 *
	 * @return
	 */
	@Override
	public Authentication authenticate(Authentication authentication) {
		
		return customAuthenticationProvider.authenticate(authentication);
		
	}

}
