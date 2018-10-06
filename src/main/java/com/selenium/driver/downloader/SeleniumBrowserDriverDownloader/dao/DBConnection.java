package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.dao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.BrowserInfo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.BrowserInfoRepo;

@EnableMongoRepositories(basePackageClasses = BrowserInfoRepo.class)
@Configuration
@EnableScheduling
public class DBConnection implements SchedulingConfigurer {

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(2);
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean
	CommandLineRunner commandLineRunner(BrowserInfoRepo userRepository) {
		return strings -> {
			userRepository.deleteAll();
			userRepository.save(new BrowserInfo("Chrome", "1", "Key"));
			userRepository.save(new BrowserInfo("Firefox", "1", "Key"));
			// userRepository.save(new BrowserInfo("Chrome", "1", "CH_Driver", "Key"));
			// userRepository.save(new BrowserInfo("Chrome", "2", "CH_Driver", "Key"));
			// userRepository.save(new BrowserInfo("Firefox", "1", "FF_Driver","Key"));
			// userRepository.save(new BrowserInfo("Firefox", "1", "FF_Driver", "Key"));
		};
	}

}
