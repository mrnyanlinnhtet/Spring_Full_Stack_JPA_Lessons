package com.jdc.location.model.record_dto;

import com.jdc.location.model.entity.State.Type;

public record StateRecordDto(int id, String name, Type type, String region) {

	public String displayName() {
		return "%s %s".formatted(name, type);
	}
}
