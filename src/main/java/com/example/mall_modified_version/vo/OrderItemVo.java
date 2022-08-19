package com.example.mall_modified_version.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: hzc
 * @date: 2022/7/31-22:21
 */
@Data
public class OrderItemVo {

    private Long orderNo;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createTime;
}
