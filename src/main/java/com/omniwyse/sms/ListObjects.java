package com.omniwyse.sms;

import java.util.List;

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
	
	
	private final DBFactory database;

	@Autowired
	public ListObjects(DBFactory database) {

		this.database = database;
		listObjects("ischool-sms");
	}
	
	public void listObjects(String bucket_name) {
		db = database.getSchoolDb();
		//System.out.format("Objects in S3 bucket %s:\n", bucket_name);
		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		ObjectListing ol = s3.listObjects(bucket_name);
		List<S3ObjectSummary> objects = ol.getObjectSummaries();
		String defaultPath = "https://s3.ap-south-1.amazonaws.com/ischool-sms/";
		Transaction transaction = db.startTransaction();
		try {
			IschoolWorksheets worksheetsLibrary = new IschoolWorksheets();
			for (S3ObjectSummary os : objects) {
				String[] str = os.getKey().split("/");
				if (str.length == 4) {
					List<IschoolWorksheets> ischool = db.where("worksheetpath = ?", defaultPath + os.getKey())
							.results(IschoolWorksheets.class);
					if (ischool.isEmpty()) {
						Long gradeid = db.where("gradenumber = ?", Long.parseLong(str[0])).results(IschoolGrades.class)
								.get(0).getId();
						Long subjectid = db.where("subjectname = ?", str[1]).results(IschoolSubjects.class).get(0)
								.getSubjectid();
						Long degreeofdifficultyid = db.where("degreeofdifficulty = ?", str[2])
								.results(IschoolLevelsOfDifficulty.class).get(0).getId();

						worksheetsLibrary.setGradeid(gradeid);
						worksheetsLibrary.setSubjectid(subjectid);
						worksheetsLibrary.setDegreeofdifficultyid(degreeofdifficultyid);
						worksheetsLibrary.setWorksheetpath(defaultPath + os.getKey());
						worksheetsLibrary.setCreatedby("OMNIWYSE");
						worksheetsLibrary.setTags(str[3].substring(0, str[3].lastIndexOf(".")));
						worksheetsLibrary.setWorksheetname(str[3].substring(0, str[3].lastIndexOf(".")));

						db.transaction(transaction).insert(worksheetsLibrary);
					}

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

}