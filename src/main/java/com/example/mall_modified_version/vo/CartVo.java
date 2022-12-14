package com.example.mall_modified_version.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 */
@Data
public class CartVo {

	private List<CartProductVo> cartProductVoList;

	private Boolean selectedAll;

	private BigDecimal cartTotalPrice;

	private Integer cartTotalQuantity;
}
