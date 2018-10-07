package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception;

public class SequenceException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errMsg;

	public SequenceException(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
