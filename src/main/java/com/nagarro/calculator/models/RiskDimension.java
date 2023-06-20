package com.nagarro.calculator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="risk_dimension")
public class RiskDimension {

	@Id
	@Column(name="dimension")
	@NotBlank(message="dimension cannot be blank")
	private String dimension;
	
	@Column(name="weight")
	private int weight;
	
}
