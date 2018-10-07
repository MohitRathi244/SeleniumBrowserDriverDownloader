package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.dao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.BrowserData;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.BrowserInfo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.MachineData;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.BrowserDataRepo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.BrowserInfoRepo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.MachineDataRepo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.service.SequenceService;

@EnableMongoRepositories(basePackageClasses = BrowserInfoRepo.class)
@Configuration
@EnableScheduling
@EnableAutoConfiguration
public class DBConnection implements SchedulingConfigurer {

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(2);
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	/*
	 * @Bean public static MongoDbFactory mongoDbFactory() throws
	 * UnknownHostException { return new SimpleMongoDbFactory(new
	 * MongoClient("ds119161.mlab.com", 19161), "s_products"); }
	 * 
	 * @Bean public static MongoTemplate mongoOperations() throws
	 * UnknownHostException { return new MongoTemplate(mongoDbFactory()); }
	 */

	@SuppressWarnings("deprecation")
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
						.allowedHeaders("*");
			}
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(BrowserInfoRepo userRepository, MachineDataRepo data, BrowserDataRepo browser,
			MongoTemplate opr) {

		return strings -> {
			SequenceService seq = new SequenceService(opr);
			final String MACHINE_SEQ_KEY = "MACHINE_KEY";
			final String BROWSER_SEQ_KEY = "BROWSER_KEY";
			userRepository.deleteAll();
			userRepository.save(new BrowserInfo("Chrome", "1", "Key"));
			userRepository.save(new BrowserInfo("Firefox", "1", "Key"));
			// System.out.println("---------------------------"+seq.getNextSequenceId(MACHINE_SEQ_KEY));
			data.deleteAll();
			data.save(new MachineData(seq.getNextSequenceId(MACHINE_SEQ_KEY), "Windows"));
			data.save(new MachineData(seq.getNextSequenceId(MACHINE_SEQ_KEY), "Mac"));
			data.save(new MachineData(seq.getNextSequenceId(MACHINE_SEQ_KEY), "Linux"));
			data.save(new MachineData(seq.getNextSequenceId(MACHINE_SEQ_KEY), "Andriod"));
			data.save(new MachineData(seq.getNextSequenceId(MACHINE_SEQ_KEY), "ios"));

			browser.deleteAll();
			browser.save(new BrowserData(seq.getNextSequenceId(BROWSER_SEQ_KEY), "Chrome"));
			browser.save(new BrowserData(seq.getNextSequenceId(BROWSER_SEQ_KEY), "Firefox"));
			browser.save(new BrowserData(seq.getNextSequenceId(BROWSER_SEQ_KEY), "IE"));

			// data.save(new MachineData(da));
			// userRepository.save(new BrowserInfo("Chrome", "1", "CH_Driver", "Key"));
			// userRepository.save(new BrowserInfo("Chrome", "2", "CH_Driver", "Key"));
			// userRepository.save(new BrowserInfo("Firefox", "1", "FF_Driver","Key"));
			// userRepository.save(new BrowserInfo("Firefox", "1", "FF_Driver", "Key"));
		};
	}

}
