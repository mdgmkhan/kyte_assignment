package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "session", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "driver_id", "device", "session_id" }) })
@JsonIgnoreProperties(ignoreUnknown = true)

public class Session implements Serializable {

	private static final long serialVersionUID = 45632L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_at")
	private Long createdAt;

	@Column(name = "session_id")
	private String sessionId;

	@Column(name = "device")
	private String device;

	@Column(name = "modified_at")
	private Long modifiedAt;

	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CommonStatus status;

}
