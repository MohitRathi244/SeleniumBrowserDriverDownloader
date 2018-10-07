package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.MachineData;

public interface MachineDataRepo extends MongoRepository<MachineData, Long>{
	
	@Query(value="{'machines':?0}")
	MachineData isMachineNameExists(String machineName);
}
