package com.tenhawks.user.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * This class has utilities related to user's object
 * @author Mukhtiar Ahmed
 *
 */
@Data
public class UserDTO {

	private String userId;

	@NotBlank(message = "Username is mandatory")
	private String userName;

	@Email(message = "Email address is mandatory")
	private String emailAddress;

	@NotBlank(message = "password is mandatory")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String fullName;

	private boolean enabled;

	private String phoneNumber;

	private String profileImage;

}
