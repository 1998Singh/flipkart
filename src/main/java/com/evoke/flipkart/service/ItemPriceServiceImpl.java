package com.evoke.flipkart.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evoke.flipkart.dto.ItemDto;
import com.evoke.flipkart.dto.ItemPriceDto;
import com.evoke.flipkart.entity.ItemEntity;
import com.evoke.flipkart.entity.ItemPriceEntity;
import com.evoke.flipkart.exception.ApiRuntimeException;
import com.evoke.flipkart.repository.ItemPriceRepository;
import com.evoke.flipkart.service.ItemPriceService;



@Service
public class ItemPriceServiceImpl implements ItemPriceService {
	private static final Logger log = LoggerFactory.getLogger(ItemPriceService.class);
	
	@Autowired
	private ItemPriceRepository itemRepository;
	
	
	
	private ModelMapper mapper = new ModelMapper();

	@Override
	public ItemPriceDto create(ItemPriceDto ItemPriceDto) {
		log.info("saving ItemPrice to database");

		try {

			ModelMapper mapper = new ModelMapper();
			ItemPriceEntity itemPriceEntity = mapper.map(ItemPriceDto, ItemPriceEntity.class);

			ItemPriceEntity createdItem = itemRepository.save(itemPriceEntity);
			log.info("saved ItemPrice to database");
			ItemPriceDto = mapper.map(createdItem, ItemPriceDto.class);
			return ItemPriceDto;

		} catch (Exception e) {
			log.error("error-saving ItemPrice to database: {}", e);
		}
		return null;
	}

	@Override
	public List<ItemPriceDto> getAll() {
		List<ItemPriceEntity> items = itemRepository.findAll();

		List<ItemPriceDto> ItemPriceDtosList = new ArrayList<>();

		for (ItemPriceEntity ItemPriceEntity : items) {

			ItemPriceDto ItemPriceDto = mapper.map(ItemPriceEntity, ItemPriceDto.class);

			ItemPriceDtosList.add(ItemPriceDto);

		}
		return ItemPriceDtosList;
	}

	@Override
	public ItemPriceDto update(ItemPriceDto itemPriceDto) {
		if (StringUtils.isEmpty(itemPriceDto.getId())) {
			throw new ApiRuntimeException("itemPriceDto ID cannot be NULL or Empty to UpdateItem", "NOT_FOUND",
					HttpStatus.NOT_FOUND);
		}

		log.info("updating item {}", itemPriceDto.toString());
		ItemPriceEntity ItemPriceEntity = mapper.map(itemPriceDto, ItemPriceEntity.class);
		
		itemRepository.save(ItemPriceEntity);
		log.info("Item updated successfully");

		ItemPriceDto updatedItemPriceDto = mapper.map(ItemPriceEntity, ItemPriceDto.class);
		return updatedItemPriceDto;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			log.info("Deleting ItemDetails  for Id {}, ", id);
			ItemPriceDto ItemPriceDto = getById(id);
			ItemPriceEntity ItemPriceEntity = mapper.map(ItemPriceDto, ItemPriceEntity.class);
			itemRepository.delete(ItemPriceEntity);
			return true;
		}catch(Exception e) {
			log.error("Error while deleting item for Id : {}", id);
			throw new  ApiRuntimeException("Error while deleting item for Id "+id, "INTERNAL_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ItemPriceDto getById(Long id) {
		log.info("Getting ItemDetails  for Id {}, ", id);
		Optional<ItemPriceEntity> ItemPriceEntityOptional = itemRepository.findById(id);
		if(ItemPriceEntityOptional.isPresent()) {
			ItemPriceEntity ItemPriceEntity = ItemPriceEntityOptional.get();
			ItemPriceDto ItemPriceDto = mapper.map(ItemPriceEntity, ItemPriceDto.class);
			return ItemPriceDto;
		}
		log.error("Item not found for Id : {}", id);
		throw new  ApiRuntimeException("Item Not Found for ID: "+id, "NOT_FOUND",HttpStatus.NOT_FOUND);
	}

	
}
