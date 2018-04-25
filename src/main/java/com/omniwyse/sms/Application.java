package com.omniwyse.sms;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String ar[]) throws IOException {
        LOGGER.info("Starting sms API");
        SpringApplication.run(Application.class, ar);
    }

}
