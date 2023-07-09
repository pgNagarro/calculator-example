package com.nagarro.calculator.dtos;


import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCapDto {
	
	@Id
	@NotBlank(message="condition cannot be blank")
	private String condition;
	
	private int totalRiskCappedScore;
}
