package com.chess4math.customer.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
@Getter
@Setter
public class EmailConfigProperties {

    private String fromAddress;

    @PostConstruct
    public void postConstruct() {
        System.out.println("Email from address: " + fromAddress);
    }


}
