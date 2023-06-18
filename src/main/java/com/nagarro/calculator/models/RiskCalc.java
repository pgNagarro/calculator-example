package com.nagarro.calculator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="risk_calc_logic")
public class RiskCalc {
	
	@Id
	@Column(name="element_name")
	private String elementName;
	
	@Column(name="formula")
	private String formula;

}
