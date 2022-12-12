package com.evoke.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evoke.flipkart.dto.AddressDto;
import com.evoke.flipkart.entity.AddressEntity;
import com.evoke.flipkart.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddessService {

	private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	private AddressRepository addressRepository;

	ModelMapper mapper = new ModelMapper();

	@Override
	public AddressDto create(AddressDto addressDto) {
		log.info("Saving Address to database");
		try {
			AddressEntity adderssEntity = mapper.map(addressDto, AddressEntity.class);
			AddressEntity addressCreated = addressRepository.save(adderssEntity);
			AddressDto address = mapper.map(addressCreated, AddressDto.class);
			return address;
		} catch (Exception e) {
			log.error("error while creating object", e);
		}
		return addressDto;
	}

	@Override
	public List<AddressDto> getAll() {
		List<AddressEntity> address = addressRepository.findAll();
		List<AddressDto> addressDtolist = new ArrayList<AddressDto>();
		for (AddressEntity addressEntity : address) {
			AddressDto addressDto = mapper.map(address, AddressDto.class);

			addressDtolist.add(addressDto);
		}
		return addressDtolist;
	}



}
