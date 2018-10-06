package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class BrowserInfo /*implements Persistable<CompositeKey> */{

	@Id
	@Field
	private CompositeKey id;

	@Field
	private DriverInfo driver;

	@Field
	private String tracker;

	/*@CreatedDate
	private Date created;

	@LastModifiedDate
	private Date modified;
	
	private boolean persisted;*/

	public BrowserInfo() {
	}
	
	public BrowserInfo(CompositeKey id, DriverInfo driver) {
		this.id = id;
		this.driver = driver;
		//this.tracker = tracker;
	}
	public BrowserInfo(String browser, String version, DriverInfo driver) {
		this.id = new CompositeKey(browser, version);
		this.driver = driver;
	}

	public BrowserInfo(CompositeKey id, DriverInfo driver, String tracker) {
		this.id = id;
		this.driver = driver;
		this.tracker = tracker;
	}
	
	public BrowserInfo(String browser, String version, String tracker) {
		this.id = new CompositeKey(browser, version);
		this.tracker = tracker;
	}
	
	public BrowserInfo(String browser, String version, DriverInfo driver, String tracker) {
		this.id = new CompositeKey(browser, version);
		this.driver = driver;
		this.tracker = tracker;
	}

	public CompositeKey getId() {
		return id;
	}

	public void setId(String browser, String version) {
		this.id = new CompositeKey(browser, version);
	}

	public void setId(CompositeKey id) {
		this.id = id;
	}

	public Object getDriver() {
		return driver;
	}

	public void setDriver(DriverInfo driver) {
		this.driver = driver;
	}

	public String getTracker() {
		return tracker;
	}

	public void setTracker(String tracker) {
		this.tracker = tracker;
	}

	/*@Override
	public boolean isNew() {
		return !persisted;
	}*/

}
