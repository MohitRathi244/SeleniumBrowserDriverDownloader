package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.BrowserData;

public interface BrowserDataRepo extends MongoRepository<BrowserData, Long>{
	
	@Query(value="{'browser':?0}")
	BrowserData isBrowserNameExists(String browserName);
}
