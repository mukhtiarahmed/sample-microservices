package com.tenhawks.person.client;


import com.tenhawks.person.dto.UserDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

/**
 * Auth Service Feign Client.
 * @author Mukhtiar Ahmed
 *
 */
@RibbonClient("auth-service")
@FeignClient(name = "auth-service")
public interface AuthServiceClient {



  @PostMapping(value = "/auth/getUser")
  UserDTO getUser(@NotNull @RequestBody final String userName);


}
