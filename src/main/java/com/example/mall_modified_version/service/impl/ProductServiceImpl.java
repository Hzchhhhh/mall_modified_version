package com.example.mall_modified_version.service.impl;

import com.example.mall_modified_version.dao.ProductMapper;
import com.example.mall_modified_version.pojo.Product;
import com.example.mall_modified_version.service.ICategoryService;
import com.example.mall_modified_version.service.IProductService;
import com.example.mall_modified_version.vo.ProductDetailVo;
import com.example.mall_modified_version.vo.ProductVo;
import com.example.mall_modified_version.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.mall_modified_version.enums.ProductStatusEnum.*;
import static com.example.mall_modified_version.enums.ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
		Set<Integer> categoryIdSet = new HashSet<>();
		if (categoryId != null) {
			categoryService.findSubCategoryId(categoryId, categoryIdSet);
			categoryIdSet.add(categoryId);
		}

		PageHelper.startPage(pageNum, pageSize);
		List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
		List<ProductVo> productVoList = productList.stream()
				.map(e -> {
					ProductVo productVo = new ProductVo();
					BeanUtils.copyProperties(e, productVo);
					return productVo;
				})
				.collect(Collectors.toList());

		PageInfo pageInfo = new PageInfo<>(productList);
		pageInfo.setList(productVoList);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<ProductDetailVo> detail(Integer productId) {
		Product product = productMapper.selectByPrimaryKey(productId);

		//???????????????????????????
		if (product.getStatus().equals(OFF_SALE.getCode())
				|| product.getStatus().equals(DELETE.getCode())) {
			return ResponseVo.error(PRODUCT_OFF_SALE_OR_DELETE);
		}

		ProductDetailVo productDetailVo = new ProductDetailVo();
		BeanUtils.copyProperties(product, productDetailVo);
		//??????????????????
		productDetailVo.setStock(product.getStock() > 100 ? 100 : product.getStock());
		return ResponseVo.success(productDetailVo);
	}
}
