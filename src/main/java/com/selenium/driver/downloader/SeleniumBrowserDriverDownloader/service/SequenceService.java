package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.service;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.SequenceId;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.SequenceException;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.SequenceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class SequenceService implements SequenceRepo {
	
	
	public MongoTemplate mongoOperation;
	
	@Autowired
	public SequenceService(MongoTemplate mongoOperation) {
		super();
		this.mongoOperation = mongoOperation;
	}

	@Override
	public long getNextSequenceId(String key) throws SequenceException {
		Query query = new Query(Criteria.where("_id").is(key));

		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true).upsert(true);

		SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);
		//System.out.println("---------------"+ seqId);
		if (seqId == null) {
			throw new SequenceException("Unable to get sequence id for key : " + key);
		}
		return seqId.getSeq();
	}

}
