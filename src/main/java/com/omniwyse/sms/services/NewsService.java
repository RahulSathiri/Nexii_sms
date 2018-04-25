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

    public int postNews(long tenantId,NewsFeed news) {
        db = retrieve.getDatabase(tenantId);
		return db.insert(news).getRowsAffected();

	}

	public List<NewsFeed> listNews(long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.sql("select * from newsfeed").results(NewsFeed.class);
	}

	public int deleteNews(NewsFeed newsfeed, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.delete(newsfeed).getRowsAffected();
	}

	public int editNews(NewsFeed newsfeed, long tenantId) {
		db = retrieve.getDatabase(tenantId);
		return db.update(newsfeed).getRowsAffected();

	}

}
