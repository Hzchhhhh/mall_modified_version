package com.example.mall_modified_version.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderCreateForm {

	@NotNull
	private Integer shippingId;
}
