package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.DriverInfo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.DownloadException;

public interface DriverInfoRepo {
	
	public DriverInfo getDriverInfo(MultipartFile file) throws IOException, DownloadException;
}
