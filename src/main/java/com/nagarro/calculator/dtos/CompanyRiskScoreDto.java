package com.nagarro.calculator.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.nagarro.calculator.models.Dimensions;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Company Risk Score Dto class
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRiskScoreDto {

	@NotBlank
	@NotNull
	private int id;
	
	private String companyName;

	private List<Dimensions> dimensions;

}
