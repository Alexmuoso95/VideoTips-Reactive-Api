package com.videotips.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Device {
	@Id
	private String id;
	@NotBlank(message = "Device.name must be present")
	private String name;
	@NotNull(message = "Device.model can not be null")
	@Positive(message = "Device.model must be positive Number ")
	private String model;
	
}
