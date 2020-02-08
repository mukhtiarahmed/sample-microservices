package com.tenhawks.client.client;

import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.UserDetail;
import com.tenhawks.client.config.FeignClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Auth Service Feign Client.
 * @author Mukhtiar Ahmed
 *
 */
@RibbonClient("user-service")
@FeignClient(name = "user-service", configuration = FeignClientConfiguration.class, decode404 = true)
public interface UserServiceClient {

  @GetMapping("/me")
  ApiResponse<UserDetail> getProfile();


}
