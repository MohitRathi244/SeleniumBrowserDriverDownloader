package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class DriverInfo {

	@Field
	private Binary driver;

	@Field
	private String fileExt;

	@Field
	private String fileName;

	@Field
	private long size;

	public DriverInfo() {
		super();
	}

	public DriverInfo(Binary driver, String fileExt, String fileName, long size) {
		super();
		this.driver = driver;
		this.fileExt = fileExt;
		this.fileName = fileName;
		this.size = size;
	}

	public Binary getDriver() {
		return driver;
	}

	public void setDriver(Binary driver) {
		this.driver = driver;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
