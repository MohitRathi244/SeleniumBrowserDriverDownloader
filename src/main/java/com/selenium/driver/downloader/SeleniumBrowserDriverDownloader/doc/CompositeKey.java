package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CompositeKey {

	private String browser;
	private String version;
	
	public CompositeKey() {
	}
	
	public CompositeKey(String browser, String version) {
		super();
		this.browser = browser;
		this.version = version;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
