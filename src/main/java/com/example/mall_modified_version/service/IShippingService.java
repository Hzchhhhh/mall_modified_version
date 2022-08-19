package com.example.mall_modified_version.service;

import com.example.mall_modified_version.form.ShippingForm;
import com.example.mall_modified_version.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author: hzc
 * @date: 2022/7/30-2:02
 */
public interface IShippingService {

    ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

    ResponseVo delete(Integer uid, Integer shippingId);

    ResponseVo update(Integer uid, Integer shippingId, ShippingForm form);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);
}
