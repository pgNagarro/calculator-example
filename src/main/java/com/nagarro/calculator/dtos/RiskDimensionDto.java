package com.nagarro.calculator.dtos;

import javax.persistence.Id;

import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Risk Dimension Dto
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskDimensionDto {

	@Id
	@NotBlank(message="dimension cannot be blank")
	private String dimension;
	
	private int weight;
	
}
