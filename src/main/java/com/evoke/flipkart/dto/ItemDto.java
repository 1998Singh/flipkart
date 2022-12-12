package com.evoke.flipkart.dto;

public class ItemDto {

	
	private Long id;
	private String name;
	private String description;
	private String category;
	private String color;
	private String brand;
    private int quantity;
    private int availableQuantity;
	
	private ItemPriceDto itemPriceDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public void addAttribute(String string, ItemDto items) {
		this.addAttribute(string, items);
	}

	public ItemPriceDto getItemPriceDto() {
		return itemPriceDto;
	}

	public void setItemPriceDto(ItemPriceDto itemPriceDto) {
		this.itemPriceDto = itemPriceDto;
	}

	@Override
	public String toString() {
		return "ItemDto [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", color=" + color + ", brand=" + brand + ", quantity=" + quantity + ", availableQuantity="
				+ availableQuantity + "]";
	
	}

}

