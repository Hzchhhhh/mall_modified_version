package com.example.mall_modified_version.service;

import com.example.mall_modified_version.form.CartAddForm;
import com.example.mall_modified_version.form.CartUpdateForm;
import com.example.mall_modified_version.pojo.Cart;
import com.example.mall_modified_version.vo.CartVo;
import com.example.mall_modified_version.vo.ResponseVo;

import java.util.List;

/**
 * @author: hzc
 * @date: 2022/7/29-18:28
 */
public interface ICartService {

    ResponseVo<CartVo> add(Integer uid, CartAddForm cartAddForm);

    ResponseVo<CartVo> list(Integer uid);

    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm form);

    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    ResponseVo<CartVo> selectAll(Integer uid);

    ResponseVo<CartVo> unSelectAll(Integer uid);

    ResponseVo<Integer> sum(Integer uid);

    List<Cart> listForCart(Integer uid);
}
