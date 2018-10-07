package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.SequenceException;

public interface SequenceRepo {

	long getNextSequenceId(String key) throws SequenceException;
}
