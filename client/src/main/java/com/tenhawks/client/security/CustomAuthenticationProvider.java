package com.tenhawks.client.security;

import com.tenhawks.client.dto.UserDTO;
import com.tenhawks.client.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.tenhawks.bean.CommonHelper.checkConfigNotNull;
import static com.tenhawks.client.util.ClientHelper.TOKEN_HEADER;
import static com.tenhawks.client.util.ClientHelper.TOKEN_PREFIX;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


	@Autowired
	private UserService userService;

	@PostConstruct
	public void init() {
		checkConfigNotNull(userService, "userService");
	}

	
	@Override
	public Authentication authenticate(Authentication authentication) {

		OAuth2AccessToken accessToken = userService.getOAuthToken(authentication);
		if(accessToken !=  null){
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpSession session = attributes.getRequest().getSession();
			session.setAttribute(TOKEN_HEADER, TOKEN_PREFIX + accessToken.getValue());
           return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
				   "N/A",
				   Arrays.asList(new SimpleGrantedAuthority("USER")));

		} else {
			throw new BadCredentialsException("Invalid username and/or password");
		}
				
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(
				UsernamePasswordAuthenticationToken.class);
	}
	

}
