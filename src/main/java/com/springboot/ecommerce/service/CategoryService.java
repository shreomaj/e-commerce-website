package com.springboot.ecommerce.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.ecommerce.entity.Category;



public interface CategoryService {

	public Category saveCategory(Category category);

	public Boolean existCategory(String name);

	public List<Category> getAllCategory();

	public Boolean deleteCategory(int id);

	public Category getCategoryById(int id);

	public List<Category> getAllActiveCategory();
	//only all active category will be shown to user
	public Page<Category> getAllCategorPagination(Integer pageNo,Integer pageSize);

}