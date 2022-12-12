package com.evoke.flipkart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoke.flipkart.dto.ItemPriceDto;
import com.evoke.flipkart.service.ItemPriceService;

@RestController
@RequestMapping("/api")
public class ItemPriceController {
	private static final Logger log = LoggerFactory.getLogger(ItemPriceController.class);

	@Autowired
	private ItemPriceService itemPriceService;

	@PostMapping("/items-price")
	public ResponseEntity<String> saveItems(@RequestBody ItemPriceDto item) {
		log.info("creating item {}", item);

		itemPriceService.create(item);
		return new ResponseEntity<String>("item created successfully", HttpStatus.CREATED);

	}

	@GetMapping("/items-price")
	public ResponseEntity<List<ItemPriceDto>> getItems(ItemPriceDto item) {
		List<ItemPriceDto> items = itemPriceService.getAll();
		return new ResponseEntity<List<ItemPriceDto>>(items, HttpStatus.OK);
	}
	
	@GetMapping("/items-price/{id}")
	public ResponseEntity<ItemPriceDto> getById(@PathVariable("id") Long id) {
		ItemPriceDto item = itemPriceService.getById(id);
		return new ResponseEntity<ItemPriceDto>(item, HttpStatus.OK);
	}
	
	@DeleteMapping("/items-price/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
		itemPriceService.delete(id);
		return new ResponseEntity<String>("Item Deleted Successfully", HttpStatus.OK);
	}
}
