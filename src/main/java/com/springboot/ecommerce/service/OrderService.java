package com.springboot.ecommerce.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.ecommerce.entity.OrderRequest;
import com.springboot.ecommerce.entity.ProductOrder;



public interface OrderService {

	public void saveOrder(Integer userid, OrderRequest orderRequest) throws Exception;
	//the products user going to order

	public List<ProductOrder> getOrdersByUser(Integer userId);

	public ProductOrder updateOrderStatus(Integer id, String status);

	public List<ProductOrder> getAllOrders();

	public ProductOrder getOrdersByOrderId(String orderId);
	
	public Page<ProductOrder> getAllOrdersPagination(Integer pageNo,Integer pageSize);
}
