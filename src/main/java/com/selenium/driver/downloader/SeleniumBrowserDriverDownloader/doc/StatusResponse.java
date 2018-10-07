package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StatusResponse {

	private Boolean status;
	private String statusMsg;
	private Object data;

	public StatusResponse(Boolean status, String statusMsg, Object data) {
		super();
		this.status = status;
		this.statusMsg = statusMsg;
		this.data = data;
	}
	
	public StatusResponse(Boolean status, String statusMsg) {
		super();
		this.status = status;
		this.statusMsg = statusMsg;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
