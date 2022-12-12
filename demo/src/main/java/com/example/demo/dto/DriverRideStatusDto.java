package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class DriverRideStatusDto implements Serializable {

	private static final long serialVersionUID = 356647L;

	private String email;
	private LocationDto location;
	private String time;
	private Long driverId;
	private Boolean availability;
}
