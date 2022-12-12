package com.evoke.flipkart.service;

import java.util.List;

import com.evoke.flipkart.dto.AddressDto;

public interface AddessService {
	
	public AddressDto create(AddressDto addressDto );

	public List<AddressDto> getAll();



}
