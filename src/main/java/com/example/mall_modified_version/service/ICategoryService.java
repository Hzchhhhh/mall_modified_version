package com.example.mall_modified_version.service;

import com.example.mall_modified_version.vo.CategoryVo;
import com.example.mall_modified_version.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * Created by 廖师兄
 */
public interface ICategoryService {

	ResponseVo<List<CategoryVo>> selectAll();

	void findSubCategoryId(Integer id, Set<Integer> resultSet);
}
