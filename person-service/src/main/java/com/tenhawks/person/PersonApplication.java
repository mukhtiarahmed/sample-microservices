package com.tenhawks.person;

import com.tenhawks.bean.RemoteTokenServiceBean;
import com.tenhawks.person.repository.ColourRepository;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;


/**
 * The Assignment Application.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties
public class PersonApplication  extends ResourceServerConfigurerAdapter {

    private static final String LOOKUP_DATA = "classpath:init-data.sql";

    @Autowired
    private ColourRepository colourRepository;

    @Autowired
    private DataSource datasource;

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(PersonApplication.class, args);
    }



    @Bean
    @Profile("default")
    InitializingBean sendDatabase() {
        return () -> {
            long count = colourRepository.count();
            if (count == 0) {
                Resource resource = context.getResource(LOOKUP_DATA);
                ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
            }
        };
    }



}
