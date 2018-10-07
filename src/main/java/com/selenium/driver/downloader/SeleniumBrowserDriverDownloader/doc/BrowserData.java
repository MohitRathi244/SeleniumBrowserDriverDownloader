package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "browserData")
public class BrowserData {

	@Id
	@Field
	private Long id;

	@Field
	private String browser;

	public BrowserData() {
		super();
	}

	public BrowserData(Long id, String browser) {
		super();
		this.id = id;
		this.browser = browser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

}
