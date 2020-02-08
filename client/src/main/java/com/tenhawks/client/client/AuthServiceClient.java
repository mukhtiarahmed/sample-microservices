package com.tenhawks.client.client;



import com.tenhawks.client.config.FeignClientConfiguration;
import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;



/**
 * Auth Service Feign Client.
 * @author Mukhtiar Ahmed
 *
 */
@RibbonClient("auth-service")
@FeignClient(name = "auth-service", configuration = FeignClientConfiguration.class, decode404 = true)
public interface AuthServiceClient {


    @RequestMapping(value ="/auth/oauth/token",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    DefaultOAuth2AccessToken oAuthToken(@RequestParam Map<String, String> formParams);


}
