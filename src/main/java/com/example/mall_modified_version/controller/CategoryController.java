package com.example.mall_modified_version.controller;

import com.example.mall_modified_version.service.ICategoryService;
import com.example.mall_modified_version.vo.CategoryVo;
import com.example.mall_modified_version.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/categories")
	public ResponseVo<List<CategoryVo>> selectAll() {
		return categoryService.selectAll();
	}
}
