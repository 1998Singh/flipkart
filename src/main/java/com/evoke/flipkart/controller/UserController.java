package com.evoke.flipkart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoke.flipkart.dto.UserCartDto;
import com.evoke.flipkart.dto.UserDto;
import com.evoke.flipkart.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<String> save(@RequestBody UserDto user) {
		log.info("creating user {}", user);
		userService.create(user);
		return new ResponseEntity<String>("user created successfully", HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getItems(UserDto user) {
		List<UserDto> users = userService.getAll();
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<UserDto> assignCartToPerson(@RequestBody UserCartDto userCartDto) {
		UserDto userDto = userService.assignCartToPerson(userCartDto.getCartid(), userCartDto.getUserid());

		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);

	}


}
