package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "machineData")
public class MachineData {

	@Id
	@Field
	private Long id;
	
	@Field
	private String machines;

	public MachineData() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MachineData(long l, String machines) {
		this.id = l;
		this.machines = machines;
	}

	public String getMachines() {
		return machines;
	}

	public void setMachines(String machines) {
		this.machines = machines;
	}

}
