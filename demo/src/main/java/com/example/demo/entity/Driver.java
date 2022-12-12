package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.demo.enums.CommonStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "driver", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@JsonIgnoreProperties(ignoreUnknown = true)

public class Driver implements Serializable {

	private static final long serialVersionUID = 112233L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_at")
	private Long createdAt;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "modified_at")
	private Long modifiedAt;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "license_number")
	private String licenseNumber;

	@Column(name = "vehicle_number")
	private String vehicleNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CommonStatus status;

}
