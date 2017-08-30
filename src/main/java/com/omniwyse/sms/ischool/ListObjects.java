package com.omniwyse.sms.ischool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class ListObjects {

	private Database db;
	private static final Logger LOGGER = LoggerFactory.getLogger(ListObjects.class);
	@Autowired
	private DBFactory database;

	public void listObjects(String bucket_name) {

		db = database.getSchoolDb();
		System.out.format("Objects in S3 bucket %s:\n", bucket_name);
		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		ObjectListing ol = s3.listObjects(bucket_name);
		List<S3ObjectSummary> objects = ol.getObjectSummaries();

		Transaction transaction = db.startTransaction();
		try {
			IschoolWorksheets worksheetsLibrary = new IschoolWorksheets();
			// Map<Integer, String[]> hm = new HashMap<Integer, String[]>();
			// int temp = 0;
			for (S3ObjectSummary os : objects) {
				String[] str = os.getKey().split("/");
				if (str.length == 4) {
					// temp++;
					System.out.println("* " + os.getKey());
					// hm.put(temp, str);
					Long gradeid = db.where("gradenumber = ?", Long.parseLong(str[0])).results(IschoolGrades.class)
							.get(0).getId();
					Long subjectid = db.where("subjectname = ?", str[1]).results(IschoolSubjects.class).get(0)
							.getSubjectid();
					Long degreeofdifficultyid = db.where("degreeofdifficulty = ?", str[2])
							.results(IschoolLevelsOfDifficulty.class).get(0).getId();

					worksheetsLibrary.setGradeid(gradeid);
					worksheetsLibrary.setSubjectid(subjectid);
					worksheetsLibrary.setDegreeofdifficultyid(degreeofdifficultyid);
					worksheetsLibrary.setWorksheetpath("" + os.getKey());
					worksheetsLibrary.setCreatedby("OMNIWYSE");
					worksheetsLibrary.setTags(str[3].substring(0, str[3].lastIndexOf(".")));
					worksheetsLibrary.setWorksheetname(str[3].substring(0, str[3].lastIndexOf(".")));
					
					db.transaction(transaction).insert(worksheetsLibrary);
				} else {
					continue;
				}
			}
			 transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Exception Occured");
		}

	}

	public static void main(String[] args) {
		ListObjects listObj = new ListObjects();
		LOGGER.info("**** Updating iSchool Worksheets Library *****");
		listObj.listObjects("ischool-sms");
	}
}