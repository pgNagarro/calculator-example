package com.nagarro.calculator.dtos;

import javax.persistence.Id;

import javax.validation.constraints.NotBlank;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Risk Score Level Dto
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskScoreLevelDto {

	@Id
	@NotBlank(message="score cannot be blank")
	private String score;

	private String level;
}
