package com.example.mall_modified_version.service;

import com.example.mall_modified_version.vo.ProductDetailVo;
import com.example.mall_modified_version.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

/**
 * Created by 廖师兄
 */
public interface IProductService {

	ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

	ResponseVo<ProductDetailVo> detail(Integer productId);
}
