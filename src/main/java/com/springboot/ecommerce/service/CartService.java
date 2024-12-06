package com.springboot.ecommerce.service;

import java.util.List;

import com.springboot.ecommerce.entity.Cart;

public interface CartService {
	public Cart saveCart(Integer productId, Integer userId);

	public List<Cart> getCartsByUser(Integer userId);
	
	public Integer getCountCart(Integer userId);
	//for particular user, how many product is in add to cart

	public void updateQuantity(String sy, Integer cid);

}
