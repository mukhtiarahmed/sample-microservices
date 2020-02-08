package com.tenhawks.client.service;

import com.tenhawks.client.client.AuthServiceClient;
import com.tenhawks.client.client.UserServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Map;

import static com.tenhawks.bean.CommonHelper.checkConfigNotNull;
import static org.springframework.security.oauth2.common.util.OAuth2Utils.GRANT_TYPE;
import static org.springframework.security.oauth2.common.util.OAuth2Utils.SCOPE;

@Slf4j
@Service
public class UserService {


    @Value("${grant-type:password}")
    private String grantType;
    @Value("${scope:trust}")
    private String scope;

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    @PostConstruct
    public void init() {
        checkConfigNotNull(authServiceClient, "authServiceClient");
        checkConfigNotNull(userServiceClient, "userServiceClient");

    }

    public OAuth2AccessToken getOAuthToken(Authentication authentication) {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        Map formParam = new HashedMap();
        formParam.put(GRANT_TYPE, grantType);
        formParam.put(SCOPE, scope);
        formParam.put("password", password);
        formParam.put("username", userName);
        OAuth2AccessToken token =  authServiceClient.oAuthToken(formParam);
        return token;
    }

}
