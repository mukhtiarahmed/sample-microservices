package com.tenhawks.person.config;

import com.tenhawks.bean.RemoteTokenServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hp on 2/4/2020.
 */
@Profile("!test")
@Configuration
@EnableResourceServer
public class ResourceServerConfig   extends ResourceServerConfigurerAdapter {


    @Primary
    @Bean
    public RemoteTokenServices tokenService(@Autowired ResourceServerProperties sso, @Autowired Environment environment) {
        return RemoteTokenServiceBean.tokenService(environment.getProperty("remote.token.store"), sso.getClientId(), sso
                .getClientSecret());
    }

    @Bean
    public CheckTokenEndpoint checkTokenEndpoint (@Autowired Environment environment, @Autowired ResourceServerProperties sso) {
        return new CheckTokenEndpoint(tokenService(sso, environment));
    }

    @Bean(name="restTemplate")
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
}
