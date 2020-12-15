package com.lambda.boot;

import com.lambda.boot.config.AmazonSDKConfig;
import com.lambda.boot.config.RedisConfig;
import com.lambda.boot.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({SecurityConfig.class, RedisConfig.class, AmazonSDKConfig.class})
@SpringBootApplication
public class BootApplication extends SpringBootServletInitializer {

    // silence console logging
    @Value("${logging.level.root:OFF}")
    String message = "";

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
