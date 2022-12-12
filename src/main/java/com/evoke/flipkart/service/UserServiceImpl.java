package com.evoke.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evoke.flipkart.dto.CartDto;
import com.evoke.flipkart.dto.ItemPriceDto;
import com.evoke.flipkart.dto.UserDto;
import com.evoke.flipkart.entity.CartEntity;
import com.evoke.flipkart.entity.ItemPriceEntity;
import com.evoke.flipkart.entity.UserEntity;
import com.evoke.flipkart.exception.ApiRuntimeException;
import com.evoke.flipkart.repository.CartRepository;
import com.evoke.flipkart.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;

	ModelMapper mapper = new ModelMapper();

	@Override
	public UserDto create(UserDto userdto) {
		
		log.info("saving User to database");
		try {
		ModelMapper mapper = new ModelMapper();
		UserEntity userentity = mapper.map(userdto, UserEntity.class);
		
		UserEntity createdUser = userRepository.save(userentity);
		userdto = mapper.map(userentity, UserDto.class);
		return userdto;
		
		}catch(Exception e) {
			log.error("Error saving user to database: {}",e);
		}
		return null;

	}

	@Override
	public List<UserDto> getAll() {

		List<UserEntity> users = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<>();
		for (UserEntity userentity : users) {
			UserDto userdto = mapper.map(userentity, UserDto.class);
			userDtoList.add(userdto);
		}

		return userDtoList;

	}

	@Override
	public UserDto update(UserDto userDto) {
		
		if (StringUtils.isEmpty(userDto.getId())) {
			throw new ApiRuntimeException("Item ID cannot be NULL or Empty to UpdateItem", "NOT_FOUND",
					HttpStatus.NOT_FOUND);
		}

		log.info("updating item {}", userDto.toString());
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);

		userRepository.save(userEntity);
		log.info("Item updated successfully");

		UserDto updatedUserDto = mapper.map(userEntity, UserDto.class);
		return updatedUserDto;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			log.info("Deleting ItemDetails  for Id {}, ", id);
			UserDto userDto = getById(id);
			UserEntity userEntity = mapper.map(userDto, UserEntity.class);
			userRepository.delete(userEntity);
			return true;
		} catch (Exception e) {
			log.error("Error while deleting item for Id : {}", id);
			throw new ApiRuntimeException("Error while deleting item for Id " + id, "INTERNAL_ERROR",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public UserDto getById(Long id) {
		return null;
//		log.info("Getting ItemDetails  for Id {}, ", id);
//			UserDto userDto = mapper.map(UserEntity, UserDto.class);
//		
//		Optional<UserEntity> cartEntityOptional = userRepository.findById(id);
//		if (cartEntityOptional.isPresent()) {
//			
//			UserEntity userEntity = cartEntityOptional.get();
//	ItemPriceEntity itemPriceEntity = userEntity.getPhonenumber();
//			if(itemPriceEntity != null) {
//				ItemPriceDto itemPriceDto = mapper.map(itemPriceEntity, ItemPriceDto.class);
//				itemDto.setItemPriceDto(itemPriceDto);
//			}
//
//			return itemDto;
//		}
//		log.error("Item not found for Id : {}", id);
//		throw new ApiRuntimeException("Item Not Found for ID: " + id, "NOT_FOUND", HttpStatus.NOT_FOUND);
	}

	@Override
	public UserDto assignCartToPerson(Long cartid, Long userid) {
		UserEntity userEntity = userRepository.findById(userid).get();
		CartEntity cartEntity = cartRepository.findById(cartid).get();
		userEntity.setCartEntity(cartEntity);
		userRepository.save(userEntity);
		UserDto userDto = mapper.map(userEntity, UserDto.class);
		CartDto cartDto = mapper.map(cartEntity, CartDto.class);
		userDto.setCartDto(cartDto);
		return userDto;
	}
}
