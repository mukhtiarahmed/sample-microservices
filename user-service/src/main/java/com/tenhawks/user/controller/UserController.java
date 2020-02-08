package com.tenhawks.user.controller;

import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.Meta;
import com.tenhawks.bean.UserDetail;
import com.tenhawks.user.bean.UserDTO;
import com.tenhawks.user.client.AuthServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;

/**
 * @author Mukhtiar Ahmed
 */
@RestController
@Slf4j
public class UserController {

  @Autowired
  private AuthServiceClient authServiceClient;



  @PutMapping("/register")
  public ApiResponse<UserDTO> register(@NotNull @RequestBody UserDTO user) {

    ApiResponse<UserDTO> response = authServiceClient.register(user);
    log.info("user register respoonse  : {} ", response.getMessage(), response.getStatus().getStatus());
    return  response;

  }



  @GetMapping("/me")
  public ApiResponse<UserDetail> getProfile(Principal principal) {
    UserDetail user = authServiceClient.getUser(principal.getName());
    ApiResponse<UserDetail> response = new ApiResponse<>();
    response.setStatus(new Meta(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    response.setData(user);
    return response;
  }


}
