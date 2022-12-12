package com.evoke.flipkart.dto;


public class UserDto {

	private Long id;
	private String name;
	private String email;
	private long phonenumber;
	private CartDto cartDto;



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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public CartDto getCartDto() {
		return cartDto;
	}

	public void setCartDto(CartDto cartDto) {
		this.cartDto = cartDto;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", phonenumber=" + phonenumber + "]";
	}

}
