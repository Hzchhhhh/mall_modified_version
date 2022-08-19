package com.example.mall_modified_version.controller;

import com.example.mall_modified_version.service.IProductService;
import com.example.mall_modified_version.vo.ProductDetailVo;
import com.example.mall_modified_version.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 廖师兄
 */
@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping("/CartVo.java")
	public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
									 @RequestParam(required = false, defaultValue = "1") Integer pageNum,
									 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return productService.list(categoryId, pageNum, pageSize);
	}

	@GetMapping("/products/{productId}")
	public ResponseVo<ProductDetailVo> detail(@PathVariable Integer productId) {
		return productService.detail(productId);
	}
}
