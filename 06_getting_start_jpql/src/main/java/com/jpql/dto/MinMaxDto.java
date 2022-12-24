package com.jpql.dto;

import java.time.LocalDate;

public record MinMaxDto(
		int id,
		String name,
		LocalDate startDate,
		LocalDate endDate
		) {

}
