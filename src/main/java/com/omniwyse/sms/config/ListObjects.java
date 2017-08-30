package com.omniwyse.sms.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.ischool.IschoolGrades;
import com.omniwyse.sms.ischool.IschoolLevelsOfDifficulty;
import com.omniwyse.sms.ischool.IschoolSubjects;
import com.omniwyse.sms.ischool.IschoolWorksheets;

@EnableAutoConfiguration
@Configuration
public class ListObjects {

	private Database db;
	
	@Autowired
	private DBFactory database;
	
	public void listObjects(String bucket_name) {

		db = database.getSchoolDb();
		System.out.format("Objects in S3 bucket %s:\n", bucket_name);
		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		ObjectListing ol = s3.listObjects(bucket_name);
		List<S3ObjectSummary> objects = ol.getObjectSummaries();
		List<IschoolGrades> grades = db.sql("select * from ischool_grades").results(IschoolGrades.class);
		List<IschoolSubjects> subjects = db.sql("select * from ischool_subjects").results(IschoolSubjects.class);
		List<IschoolLevelsOfDifficulty> level = db.sql("select * from ischool_degreeofdifficulty").results(IschoolLevelsOfDifficulty.class);
		
		Transaction transaction = db.startTransaction();
		try {
			IschoolWorksheets worksheetsLibrary = new IschoolWorksheets();
			Map<Integer, String[]> hm = new HashMap<Integer, String[]>();
			int temp = 0;
			for (S3ObjectSummary os : objects) {
				String[] str = os.getKey().split("/");
				if (str.length == 4) {
					temp++;
					System.out.println("* " + os.getKey());
					hm.put(temp, str);

				} else {
					continue;
				}
			}
			for (int i = 0; i < hm.size(); i++) {

			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Exception Occured");
		}

	}
}