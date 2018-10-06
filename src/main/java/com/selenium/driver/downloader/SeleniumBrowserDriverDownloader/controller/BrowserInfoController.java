package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.BrowserInfo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.CompositeKey;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.DownloadException;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.BrowserInfoRepo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.DriverInfoRepo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.service.FileStorageService;

@RestController
@RequestMapping("/browsers/data")
public class BrowserInfoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BrowserInfoController.class);
	
	private BrowserInfoRepo controller;
	private DriverInfoRepo driverController;
	
	public BrowserInfoController(BrowserInfoRepo controller) {
		super();
		this.controller = controller;
	}

	@GetMapping("/all")
	public List<BrowserInfo> getAll() {
		return controller.findAll();
	}

	/*@GetMapping("/{browser}/{version}")
	public BrowserInfo getSpecific(@PathVariable String browser, @PathVariable String version) throws Exception {
		Optional<BrowserInfo> driver = controller.findById(new CompositeKey(browser, version));
		if (!driver.isPresent())
			throw new Exception("Driver Not Found for : " + browser + " browser and " + version + " version pair.");
		return driver.get();
	}*/

	@GetMapping("/all/{browser}")
	public List<String> getSpecificByBrowser(@PathVariable String browser) throws Exception {
		return (List<String>) controller.findByBrowserName(browser);
	}

	@PostMapping("/add")
	public Boolean insertData(@Valid @RequestBody BrowserInfo info) {
		controller.save(info);
		return true;
	}

	@PostMapping("/add/driver")
	public BrowserInfo insertDriverData(@RequestParam String browser,@RequestParam String version,
			@RequestParam("file") MultipartFile uploadfile) throws IOException, DownloadException {	
		/*ObjectMapper obj=new ObjectMapper();
		CompositeKey userInfo=obj.readValue(info, CompositeKey.class);*/
		CompositeKey info = new CompositeKey(browser, version);
		BrowserInfo b=controller.insert(new BrowserInfo(info,driverController.getDriverInfo(uploadfile)));
		return b;
	}
	
	@GetMapping("/{browser}/{version}")
	public BrowserInfo getSpecific(@PathVariable String browser, @PathVariable String version) throws Exception {
		Optional<BrowserInfo> driver = controller.findById(new CompositeKey(browser, version));
		if (!driver.isPresent())
			throw new Exception("Driver Not Found for : " + browser + " browser and " + version + " version pair.");
		return driver.get();
	}
	
	
	@GetMapping("/retrieve/{browser}/{version}")
	public String retrieveDriverFile(@PathVariable String browser, @PathVariable String version) throws Exception {
		Optional<BrowserInfo> driver = controller.findById(new CompositeKey(browser, version));
		if (!driver.isPresent())
			throw new Exception("Driver Not Found for : " + browser + " browser and " + version + " version pair.");
	    Binary document = (Binary) driver.get().getDriver();
	    if(document != null) {
	        FileOutputStream fileOuputStream = null;
	        try {
	            fileOuputStream = new FileOutputStream("C:\\Users\\morathi\\Desktop\\"+"driver.txt");
	            fileOuputStream.write(document.getData());
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "failure";
	        } finally {
	            if (fileOuputStream != null) {
	                try {
	                    fileOuputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    return "failure";
	                }
	            }
	        }
	    }
	    return "success";		
	}

	///////////////////////////////////////////////////////////////////////
/*
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}*/
	/*
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}*/

}
