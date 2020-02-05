package com.tenhawks.auth;

import com.datastax.driver.core.utils.UUIDs;
import com.tenhawks.auth.domain.Client;
import com.tenhawks.auth.domain.Role;
import com.tenhawks.auth.domain.User;
import com.tenhawks.auth.repository.ClientRepository;
import com.tenhawks.auth.repository.RoleRepository;
import com.tenhawks.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.tenhawks.bean.CommonHelper.STRENGTH;


/**
 * @author Mukhtiar Ahmed
 */

@Slf4j
@SpringBootApplication
@EnableGlobalMethodSecurity (prePostEnabled = true)
@Configuration
@EnableCassandraRepositories(basePackages = "com.tenhawks.auth")
@EnableFeignClients
@EnableDiscoveryClient
@EnableResourceServer
public class AuthApplication {



	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public AuthenticationKeyGenerator getAuthenticationKeyGenerator() {
		return new DefaultAuthenticationKeyGenerator();
	}

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(STRENGTH);
    }


    @Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		List<String> mappingFiles = Arrays.asList(
				"dozer-configration-mapping.xml"
		);

		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(mappingFiles);
		return dozerBean;
	}

	@Bean
	InitializingBean sendDatabase() {

		return new InitializingBean() {

            @Autowired
            private ClientRepository clientRepository;

            @Autowired
            private RoleRepository roleRepository;

            @Autowired
            private UserRepository userRepository;

            public void afterPropertiesSet()  {

                Client client = clientRepository.findByClientId(UUID.fromString("cffe3990-6f0e-11e8-b750-4d8614c940ff"));
                if(client == null) {
                    log.info("add initial data");
                    client = new Client();
                    client.setAccessTokenValiditySeconds(2592000);
                    client.setRefreshTokenValiditySeconds(2592000 * 30);
                    client.setClientId(UUID.fromString("cffe3990-6f0e-11e8-b750-4d8614c940ff"));
                    // password is  `secert`
                    client.setClientSecret("$2a$12$.5qlSA.5Gjp9.TRlEflnXukLlYUz/eNRhLgFKubk6PIoL8GM7GzLu");
                    client.getScope().add("trust");
                    client.getAuthorizedGrantTypes().add("password");
                    client.getAuthorizedGrantTypes().add("refresh_token");
                    clientRepository.save(client);


                    client = new Client();
                    client.setAccessTokenValiditySeconds(2592000);
                    client.setRefreshTokenValiditySeconds(2592000 * 30);
                    client.setClientId(UUID.fromString("cffe3990-6f0e-11e8-b750-4d8614c940f0"));
                    // password is  `secert`
                    client.setClientSecret("$2a$12$.5qlSA.5Gjp9.TRlEflnXukLlYUz/eNRhLgFKubk6PIoL8GM7GzLu");
                    client.getScope().add("server");
                    client.getAuthorizedGrantTypes().add("client_credentials");
                    client.getAuthorizedGrantTypes().add("refresh_token");
                    clientRepository.save(client);

                    Role role = new Role();
                    role.setId(UUIDs.timeBased());
                    role.setRoleName("ROLE_USER");
                    roleRepository.save(role);

                    role = new Role();
                    role.setId(UUIDs.timeBased());
                    role.setRoleName("ROLE_ADMIN");
                    roleRepository.save(role);

                    role = new Role();
                    role.setId(UUIDs.timeBased());
                    role.setRoleName("ROLE_SUPER_ADMIN");
                    roleRepository.save(role);

                    User user = new User();
                    user.setUserId(UUIDs.timeBased());
                    user.setRoles(Arrays.asList(role.getRoleName()));
                    user.setEmailAddress("mukhtiar.ahmed@gmail.com");
                    user.setActive(true);
                    user.setFullName("Mukhtiar Ahmed");
                    user.setUserName("ahmed");
                    user.setPhoneNumber("+923100000000");
                    // password is  `secert`
                    user.setPassword("$2a$12$2kV1gT4c3XM.Gl1jHPoLYO/V7Pg.A1KnlUlZBrf5rnDXjYNLgc6N6");
                    userRepository.save(user);
                    user = userRepository.findByUserName("ahmed");
                    user.getRoles();
                } else {
                    log.info("initial data already exists");
                }
            }
		};

	}

}
