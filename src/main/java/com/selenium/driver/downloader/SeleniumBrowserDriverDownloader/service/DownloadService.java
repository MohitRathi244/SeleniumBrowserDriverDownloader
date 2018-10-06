package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.service;

import org.springframework.stereotype.Component;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.DownloadException;

@Component
public class DownloadService {

	public String retrieveDriver(String browser, String version) throws DownloadException {

		return version;
	}

	

	private boolean verifyBrowserVer(String browser, String version) throws DownloadException {
		if (verifyBrowser(browser)) {
			
		} else {
			throw new DownloadException("Passed Browser [" + browser + "] is not supported.");
		}
		return false;
	}

	private boolean verifyBrowser(String browser) {
		return isValidEnum(SupportedBrowser.class,browser.toUpperCase());
	}
	
	enum SupportedBrowser {
		CHROME, IE, FIREFOX
	};
	
	private <E extends Enum<E>> boolean isValidEnum(final Class<E> enumClass, final String enumName) {
        if (enumName == null) {
            return false;
        }
        try {
            Enum.valueOf(enumClass, enumName);
            return true;
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }
	
	
}
