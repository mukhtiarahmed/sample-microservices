package com.tenhawks.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mukhtiar Ahmed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 12112526L;

	private String userId;

	private String userName;

	private String emailAddress;

	private String fullName;

	private String profileImage;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private Boolean active = Boolean.FALSE;

	private String phoneNumber;

	private List<String> roles =new ArrayList<>();


	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return this.fullName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	@JsonIgnore
	public List<GrantedAuthority> getAuthorities() {
		return roles.stream().map( role -> new SimpleGrantedAuthority(role)).collect( Collectors.toList());
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.active;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

	public void setActive(boolean active) {
		 this.active = active;
	}


	public void setEnabled(boolean enable) {
		 this.active = enable;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}





}
