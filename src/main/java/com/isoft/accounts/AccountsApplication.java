package com.isoft.accounts;

import com.isoft.accounts.dto.DataBaseConfigDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.isoft.accounts.repository")
@EntityScan("com.isoft.accounts.entity")
@EnableFeignClients
@EnableConfigurationProperties({DataBaseConfigDTO.class})
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.isoft.commons", "com.isoft.accounts"})
@EnableJpaAuditing(auditorAwareRef = "getAuditAware")
@OpenAPIDefinition(
        info = @Info(
                title = "Isoft Accounts microservice",
                description = "Accounts service that serves accounting businesses for its customers",
                contact = @Contact(
                        email = "mramz55555@gmail.com",
                        url = "https://github.com/mramz55555"
                ),
                license = @License(
                        name = "Apache 2.0"
                ),
                version = "1.0.0 SNAPSHOT"
        ),
        externalDocs = @ExternalDocumentation(
                url = "https://docs.oracle.com/en/java/javase/22/docs/api/index.html",
                description = "java documentation by oracle"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}