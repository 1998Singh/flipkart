package com.evoke.flipkart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evoke.flipkart.dto.AddressDto;
import com.evoke.flipkart.service.AddessService;

@Controller
@RequestMapping("/api")
public class AddressController {

	private static final Logger log = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddessService addressService;

	@PostMapping("/address")
	public ResponseEntity<AddressDto> create(@RequestBody AddressDto addressDto) {
		log.info("Address saved successfully{}", addressDto);
		AddressDto address = addressService.create(addressDto);

		return new ResponseEntity<AddressDto>(address, HttpStatus.CREATED);

	}

	@GetMapping("/address")
	public ResponseEntity<List<AddressDto>> getall(@PathVariable AddressDto addressDto) {
		log.info("Get address");
		List<AddressDto> address = addressService.getAll();
		return new ResponseEntity<List<AddressDto>>(address, HttpStatus.OK);

	}

}
