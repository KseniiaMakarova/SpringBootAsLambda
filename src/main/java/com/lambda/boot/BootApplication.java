package com.lambda.boot;

import com.lambda.boot.config.SecurityConfig;
import com.lambda.boot.config.WebSocketConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import({SecurityConfig.class, WebSocketConfig.class})
@SpringBootApplication
public class BootApplication extends SpringBootServletInitializer {

    // silence console logging
    @Value("${logging.level.root:OFF}")
    String message = "";

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
