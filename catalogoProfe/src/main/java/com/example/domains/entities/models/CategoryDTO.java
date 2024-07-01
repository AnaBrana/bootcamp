package com.example.domains.entities.models;

import java.io.Serializable;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;



public class CategoryDTO implements Serializable{
	@JsonProperty("id")
	private int categoryId;
	@JsonProperty("nombre")
	private String name;
	
	
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public CategoryDTO(int categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}
	
	
	public static CategoryDTO from(Category source) {
		return new CategoryDTO(
				source.getCategoryId(), 
				source.getName() 
				
				);
	}
	public static Category from(CategoryDTO source) {
		return new Category(
				source.getCategoryId(), 
				source.getName()
			
				);
	}
	
	

}
