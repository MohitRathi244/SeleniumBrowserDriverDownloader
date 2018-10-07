package com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.controller;

import java.rmi.UnknownHostException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.MachineData;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.doc.StatusResponse;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.DownloadException;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.exception.SequenceException;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.repo.MachineDataRepo;
import com.selenium.driver.downloader.SeleniumBrowserDriverDownloader.service.SequenceService;

@RestController
@RequestMapping("/machine/data")
public class MachineDataController {

	public final String MACHINE_SEQ_KEY = "MACHINE_KEY";

	@Autowired
	private MachineDataRepo machineController;

	@Autowired
	private SequenceService ser;

	/*
	 * public MachineDataController(MachineDataRepo machineController) { super();
	 * this.machineController = machineController; }
	 */

	/*
	 * @GetMapping("/all") public List<MachineData> getAll() { return
	 * machineController.findAll(); }
	 */

	@GetMapping("/all")
	public ResponseEntity<StatusResponse> getAll() {
		List<MachineData> data = machineController.findAll();
		if (data.isEmpty()) {
			return new ResponseEntity<StatusResponse>(new StatusResponse(false, "No Data Found", data), HttpStatus.OK);
		}
		return new ResponseEntity<StatusResponse>(new StatusResponse(true, "Data Found", data), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<StatusResponse> insertData(@Valid @RequestParam String info)
			throws SequenceException, DownloadException, UnknownHostException {
		if (machineController.isMachineNameExists(info) == null) {
			machineController.save(new MachineData(ser.getNextSequenceId(MACHINE_SEQ_KEY), info));
			return new ResponseEntity<StatusResponse>(new StatusResponse(true, "Data added successfully"),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<StatusResponse>(new StatusResponse(false, "Data aleardy exits"),
					HttpStatus.FOUND);
		}
	}

	@PostMapping("/verify")
	public ResponseEntity<StatusResponse> verifyData(@Valid @RequestParam String info)
			throws SequenceException, DownloadException, UnknownHostException {
		if (machineController.isMachineNameExists(info) == null) {
			return new ResponseEntity<StatusResponse>(new StatusResponse(false, info + " is not found"), HttpStatus.OK);
		} else {
			return new ResponseEntity<StatusResponse>(new StatusResponse(true, info + " is aleardy exists."),
					HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{info}")
	public ResponseEntity<StatusResponse> deleteData(@Valid @PathVariable String info)
			throws SequenceException, DownloadException, UnknownHostException {
		MachineData data = machineController.isMachineNameExists(info);
		if (data == null) {
			return new ResponseEntity<StatusResponse>(new StatusResponse(false, info + " not found"), HttpStatus.OK);
		} else {
			machineController.delete(data);
			return new ResponseEntity<StatusResponse>(new StatusResponse(true, data.getMachines() + " is deleted"),
					HttpStatus.OK);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<StatusResponse> updateData(@Valid @RequestParam String info, @Valid @PathVariable long id)
			throws SequenceException, DownloadException, UnknownHostException {
		Optional<MachineData> data = machineController.findById(id);
		if (!data.isPresent())
			return new ResponseEntity<StatusResponse>(new StatusResponse(false, info + " not found"), HttpStatus.OK);
		machineController.save(new MachineData(id, info));
		return new ResponseEntity<StatusResponse>(new StatusResponse(true, id + " updated successfully"),
				HttpStatus.OK);
	}
}
