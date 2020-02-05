package com.tenhawks.bean;

import com.tenhawks.exception.InitializationNotAllowedException;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;


/**
 * @author Mukhtiar Ahmed
 */

public final class RemoteTokenServiceBean {

  private RemoteTokenServiceBean() throws InitializationNotAllowedException {
    throw new InitializationNotAllowedException("nice try!!!");
  }

  public static RemoteTokenServices tokenService(final String checkTokenUrl, final String clientId, final String
      clientSecret) {
    RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setCheckTokenEndpointUrl(checkTokenUrl);
    tokenService.setClientId(clientId);
    tokenService.setClientSecret(clientSecret);
    return tokenService;
  }

}
