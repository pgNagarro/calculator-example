package com.nagarro.calculator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="risk_dimension")
public class RiskDimension {

	@Id
	@Column(name="dimension")
	private String dimension;
	
	@Column(name="weight")
	private int weight;
	
}
