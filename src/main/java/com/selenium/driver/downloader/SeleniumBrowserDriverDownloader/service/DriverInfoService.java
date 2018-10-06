package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.DriverInfo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.DownloadException;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.DriverInfoRepo;

@Service
public class DriverInfoService implements DriverInfoRepo{
		
	public DriverInfo getDriverInfo(MultipartFile file) throws IOException, DownloadException {
		if(file==null)
			throw new DownloadException("File Not Found");
		return new DriverInfo(new Binary(BsonBinarySubType.BINARY, file.getBytes()), file.getContentType(), file.getName(), file.getSize());		
	}
}
