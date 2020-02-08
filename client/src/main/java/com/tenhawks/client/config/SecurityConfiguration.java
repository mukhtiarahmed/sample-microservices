package com.tenhawks.client.config;


import com.tenhawks.client.security.CustomAuthenticationManager;
import com.tenhawks.client.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()  {
		return new CustomAuthenticationManager(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.
		authorizeRequests()		
		.antMatchers("/login").permitAll()
	    .anyRequest()
		.authenticated().and().csrf().disable().formLogin()
		.loginPage("/login").failureUrl("/login?error=true")
		.defaultSuccessUrl("/home")
		.usernameParameter("username")
		.passwordParameter("password")
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/").and().exceptionHandling()
		.accessDeniedPage("/access-denied");
	}

	
	@Override
	public void configure(WebSecurity web)  {
		web
		.ignoring()
		.antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**");
	}

}
