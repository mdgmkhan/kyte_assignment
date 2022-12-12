package com.example.demo.convertor;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.example.demo.dto.LocationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationDtoJson implements AttributeConverter<LocationDto, String> {
	private final static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(LocationDto meta) {
		try {
			if (meta == null)
				return null;
			return objectMapper.writeValueAsString(meta);
		} catch (JsonProcessingException ex) {
			return null;
		}
	}

	@Override
	public LocationDto convertToEntityAttribute(String dbData) {
		try {
			if (dbData == null)
				return null;
			return objectMapper.readValue(dbData, LocationDto.class);
		} catch (IOException ex) {
			return null;
		}
	}
}
