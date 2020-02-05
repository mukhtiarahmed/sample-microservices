package com.tenhawks.user.client;

import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.UserDetail;
import com.tenhawks.user.bean.UserDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Auth Service Feign Client.
 * @author Mukhtiar Ahmed
 *
 */
@RibbonClient("auth-service")
@FeignClient(name = "auth-service")
public interface AuthServiceClient {

  @PutMapping(value = "/auth/signUpUser", consumes = MediaType
      .APPLICATION_JSON_UTF8_VALUE)
  ApiResponse<UserDTO> register(@NotNull @RequestBody UserDTO user);


  @PostMapping("/auth/getUser")
  UserDetail getUser(@NotNull @RequestBody String userName);


}
