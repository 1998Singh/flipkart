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
import com.evoke.flipkart.repository.ItemRepository;
import com.evoke.flipkart.service.ItemService;




@Service
public class ItemServiceImpl implements ItemService {
	
	private static final Logger log = LoggerFactory.getLogger(ItemPriceServiceImpl.class);
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemPriceRepository itemPriceRepository;
	
	private ModelMapper mapper = new ModelMapper();

	@Override
	public ItemDto create(ItemDto itemDto) {
		log.info("saving items to database");

		try {

			ModelMapper mapper = new ModelMapper();
			ItemEntity itemEntity = mapper.map(itemDto, ItemEntity.class);

			ItemEntity createdItem = itemRepository.save(itemEntity);
			log.info("saved Item to database");
			itemDto = mapper.map(createdItem, ItemDto.class);
			return itemDto;

		} catch (Exception e) {
			log.error("error-saving item to database: {}", e);
		}
		return null;
	}

	@Override
	public List<ItemDto> getAll() {
		List<ItemEntity> items = itemRepository.findAll();

		List<ItemDto> itemDtosList = new ArrayList<>();

		for (ItemEntity itemEntity : items) {

			ItemDto itemDto = mapper.map(itemEntity, ItemDto.class);

			itemDtosList.add(itemDto);

		}
		return itemDtosList;
	}

	@Override
	public ItemDto update(ItemDto itemDto) {
		if (StringUtils.isEmpty(itemDto.getId())) {
			throw new ApiRuntimeException("Item ID cannot be NULL or Empty to UpdateItem", "NOT_FOUND",
					HttpStatus.NOT_FOUND);
		}

		log.info("updating item {}", itemDto.toString());
		ItemEntity itemEntity = mapper.map(itemDto, ItemEntity.class);

		itemRepository.save(itemEntity);
		log.info("Item updated successfully");

		ItemDto updatedItemDto = mapper.map(itemEntity, ItemDto.class);
		return updatedItemDto;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			log.info("Deleting ItemDetails  for Id {}, ", id);
			ItemDto itemDto = getById(id);
			ItemEntity itemEntity = mapper.map(itemDto, ItemEntity.class);
			itemRepository.delete(itemEntity);
			return true;
		} catch (Exception e) {
			log.error("Error while deleting item for Id : {}", id);
			throw new ApiRuntimeException("Error while deleting item for Id " + id, "INTERNAL_ERROR",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ItemDto getById(Long id) {
		log.info("Getting ItemDetails  for Id {}, ", id);
		Optional<ItemEntity> itemEntityOptional = itemRepository.findById(id);
		if (itemEntityOptional.isPresent()) {
			
			ItemEntity itemEntity = itemEntityOptional.get();
			ItemDto itemDto = mapper.map(itemEntity, ItemDto.class);
			
			ItemPriceEntity itemPriceEntity = itemEntity.getItemPrice();
			if(itemPriceEntity != null) {
				ItemPriceDto itemPriceDto = mapper.map(itemPriceEntity, ItemPriceDto.class);
				itemDto.setItemPriceDto(itemPriceDto);
			}
			
			
			
			return itemDto;
		}
		log.error("Item not found for Id : {}", id);
		throw new ApiRuntimeException("Item Not Found for ID: " + id, "NOT_FOUND", HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ItemDto assignPriceToItem(Long itemId, Long priceId) {
		
		ItemEntity itemEntity = itemRepository.findById(itemId).get();
		
		ItemPriceEntity itemPriceEntity = itemPriceRepository.findById(priceId).get();
		
		itemEntity.setItemPrice(itemPriceEntity);
		
		itemRepository.save(itemEntity);
		
		ItemDto itemDto =  mapper.map(itemEntity, ItemDto.class);
		
		ItemPriceDto itemPriceDto = mapper.map(itemPriceEntity, ItemPriceDto.class);
		
		itemDto.setItemPriceDto(itemPriceDto);
		
		return itemDto;
	}
}