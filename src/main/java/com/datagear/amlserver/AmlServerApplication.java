package com.datagear.amlserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
@OpenAPIDefinition(info = @Info(title = "Library APIS", version = "1.0", description = "Library Management Apis."))
public class AmlServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmlServerApplication.class, args);
    }
}
