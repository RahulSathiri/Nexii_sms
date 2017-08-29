package com.omniwyse.sms.config;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@EnableAutoConfiguration
@Configuration
public class ListObjects {

	public void listObjects(String bucket_name) {

		System.out.format("Objects in S3 bucket %s:\n", bucket_name);
		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		ObjectListing ol = s3.listObjects(bucket_name);
		List<S3ObjectSummary> objects = ol.getObjectSummaries();
		for (S3ObjectSummary os : objects) {
			String[] str = os.getKey().split("/");
			if (str.length == 4) {
				System.out.println("* " + os.getKey());
				for (int i = 0; i < str.length; i++) {
					if (str[i] == "1") {

					}
				}
			} else {
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		ListObjects listObj = new ListObjects();
        listObj.listObjects("ischool-sms");
	}
}