package com.evoke.flipkart.service;

import java.util.List;

import com.evoke.flipkart.dto.UserDto;

public interface UserService {
	
	
	public UserDto create(UserDto userdto);
	
	public List<UserDto> getAll();
	
	public UserDto update(UserDto userDto);

	public Boolean delete(Long id);

	public UserDto getById(Long id);
	
	public UserDto assignCartToPerson(Long cartid, Long userid);
	
	


}
