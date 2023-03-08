package com.jdc.location.model.dto;

public interface StateWithDistrictCountDto {

	int getId();

	String getName();

	int getDistrictCount();

	default void show() {
		System.out.println(
				"Id: %2d , Name: %10s , District Count: %d%n".formatted(getId(), getName(), getDistrictCount()));
	}

}
