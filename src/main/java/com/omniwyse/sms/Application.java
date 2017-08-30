package com.omniwyse.sms;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.omniwyse.sms.ischool.ListObjects;


@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String ar[]) throws IOException {
        LOGGER.info("Starting sms API");
        SpringApplication.run(Application.class, ar);
        ListObjects listObj = new ListObjects();
		LOGGER.info("**** Updating iSchool Worksheets Library *****");
		listObj.listObjects("ischool-sms");
    }

}
