package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.models.NewsFeed;

@Service
public class NewsService {

	@Autowired
    com.omniwyse.sms.db.DatabaseRetrieval retrieve;
	private Database db;

	public int postNews(NewsFeed news) {
		db = retrieve.getDatabase(1);
		return db.insert(news).getRowsAffected();

	}

	public List<NewsFeed> listNews() {
		db = retrieve.getDatabase(1);
		return db.sql("select * from newsfeed").results(NewsFeed.class);
	}

	public int deleteNews(NewsFeed newsfeed) {
		db = retrieve.getDatabase(1);
		return db.delete(newsfeed).getRowsAffected();
	}

	public int editNews(NewsFeed newsfeed) {
		db = retrieve.getDatabase(1);
		return db.update(newsfeed).getRowsAffected();

	}

}
