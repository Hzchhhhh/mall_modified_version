package com.example.mall_modified_version.service.impl;

import com.example.mall_modified_version.dao.CategoryMapper;
import com.example.mall_modified_version.pojo.Category;
import com.example.mall_modified_version.service.ICategoryService;
import com.example.mall_modified_version.vo.CategoryVo;
import com.example.mall_modified_version.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.example.mall_modified_version.consts.MallConst.ROOT_PARENT_ID;

/**
 * Created by 廖师兄
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 耗时：http(请求微信api) > 磁盘 > 内存
	 * mysql(内网+磁盘)
	 * @return
	 */
	@Override
	public ResponseVo<List<CategoryVo>> selectAll() {
		List<Category> categories = categoryMapper.selectAll();

		//查出parent_id=0
		List<CategoryVo> categoryVoList = new ArrayList<>();
		for (Category category : categories) {
			if (category.getParentId().equals(ROOT_PARENT_ID)) {
				CategoryVo categoryVo = new CategoryVo();
				BeanUtils.copyProperties(category, categoryVo);
				categoryVoList.add(categoryVo);
			}
		}

		//e就是Category对象，只是自定义的名字而已
//		List<CategoryVo> categoryVoList = categories.stream()
//				.filter(e -> e.getParentId().equals(ROOT_PARENT_ID))
//				.map(this::category_to_CategoryVo)
//				.sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
//				.collect(Collectors.toList());

//		查询子目录
		findSubCategory(categoryVoList, categories);

		return ResponseVo.success(categoryVoList);
	}


	//方法重载，避免多次查询数据库
	@Override
	public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
		List<Category> categories = categoryMapper.selectAll();
		findSubCategoryId(id, resultSet, categories);
	}

	private void findSubCategoryId(Integer id, Set<Integer> resultSet, List<Category> categories) {
		for (Category category : categories) {
			if (category.getParentId().equals(id)) {
				resultSet.add(category.getId());
				//递归
				findSubCategoryId(category.getId(), resultSet, categories);
			}
		}
	}

	private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories) {
		for (CategoryVo categoryVo : categoryVoList) {
			//子类目列表
			List<CategoryVo> subCategoryVoList = new ArrayList<>();

			for (Category category : categories) {
				//如果查到内容，设置subCategory, 继续往下查
				if (categoryVo.getId().equals(category.getParentId())) {
					//把查到的子类目转成CategoryVo类型返回
					CategoryVo subCategoryVo = category_to_CategoryVo(category);
					subCategoryVoList.add(subCategoryVo);
				}

				subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
				categoryVo.setSubCategories(subCategoryVoList);

				findSubCategory(subCategoryVoList, categories);
			}
		}
	}

	private CategoryVo category_to_CategoryVo(Category category) {
		CategoryVo categoryVo = new CategoryVo();
		BeanUtils.copyProperties(category, categoryVo);
		return categoryVo;
	}
}
