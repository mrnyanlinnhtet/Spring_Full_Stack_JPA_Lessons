package com.ultron.ineritance.converter;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColorConverter implements AttributeConverter<Color, String> {

	@Override
	public String convertToDatabaseColumn(Color color) {
		if (null != color) {
			return "%s,%s,%s".formatted(color.getRed(), color.getGreen(), color.getBlue());
		}
		return null;
	}

	@Override
	public Color convertToEntityAttribute(String str) {
		if (null != str && !str.isBlank()) {
			var color = str.split(",");
			return new Color(getColorCode(color[0]), getColorCode(color[1]), getColorCode(color[2]));
		}
		return null;
	}

	private float getColorCode(String colorCode) {
		var result = new BigDecimal(colorCode);
		return result.divide(new BigDecimal(255),16,RoundingMode.HALF_UP).floatValue();
	}

}
