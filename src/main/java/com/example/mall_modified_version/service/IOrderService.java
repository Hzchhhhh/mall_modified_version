package com.example.mall_modified_version.service;

import com.example.mall_modified_version.vo.OrderVo;
import com.example.mall_modified_version.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

/**
 * @author: hzc
 * @date: 2022/7/31-22:23
 */
public interface IOrderService {

    ResponseVo<OrderVo> create(Integer uid, Integer shippingId);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);

    ResponseVo cancel(Integer uid, Long orderNo);

    void paid(Long orderNo);
}
