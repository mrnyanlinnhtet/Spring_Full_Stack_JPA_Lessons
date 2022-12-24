package com.jpql.dto;

public record SumDto(
		int townshipId,
		String townshipName,
		int productId,
		String productName,
		int price,
		long quantity
		) {

}
