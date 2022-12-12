package com.evoke.flipkart.service;

import java.util.List;

import com.evoke.flipkart.dto.ItemPriceDto;

public interface ItemPriceService {

	
	public ItemPriceDto  create(ItemPriceDto item);

	public List<ItemPriceDto> getAll();

	public ItemPriceDto update(ItemPriceDto ItemPriceDto);

	public Boolean delete(Long id);

	public ItemPriceDto getById(Long id);
	


}

