package com.nagarro.calculator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="company_risk_score")
public class CompanyRiskScore {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="company_name")
	@NotBlank(message="Company name cannot be blank")
	private String companyName;
	
	@Column(name="information_security")
	private int informationSecurity;
	
	@Column(name="resilience")
	private int resilience;
	
	@Column(name="conduct")
	private int conduct;

}
