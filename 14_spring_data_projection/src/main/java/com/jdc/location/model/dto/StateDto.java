package com.jdc.location.model.dto;

import com.jdc.location.model.entity.State.Type;

public interface StateDto {

	int getId();

	String getName();

	Type getType();

	String getRegion();

	int getPorpulation();
}
