package com.tenhawks.client.config;


import com.tenhawks.client.util.ClientHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import static com.tenhawks.client.util.ClientHelper.TOKEN_HEADER;

/**
 * @author Mukhtiar Ahmed
 */
public class FeignClientConfiguration {



    @Bean
    public AuthInterceptor bearerHeaderAuthRequestInterceptor(@Autowired Environment  environment) {
        return new AuthInterceptor(environment);
    }

    @Bean
    public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }
}

@Slf4j
class AuthInterceptor implements RequestInterceptor {

    private String  clientId;

    private String clientSecret;

    protected AuthInterceptor(Environment  environment){
        clientId = environment.getProperty("clientId");
        clientSecret = environment.getProperty("clientSecret");
    }
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);
        if(session != null) {
            String accessToken =  (String) session.getAttribute(TOKEN_HEADER);
            if(accessToken != null) {
                template.header(TOKEN_HEADER, accessToken);
                return;
            }
        }

        template.header(TOKEN_HEADER, ClientHelper.getAuthorizationHeader(clientId, clientSecret));

    }


}