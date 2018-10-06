package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.BrowserInfo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.CompositeKey;

public interface BrowserInfoRepo extends MongoRepository<BrowserInfo, CompositeKey> {

	@Query(value = "{ '_id.browser': ?0  }", fields = "{'_id.version' : 1}")
	List<String> findByBrowserName(String browser);
	
}
