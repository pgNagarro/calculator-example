package com.nagarro.calculator.models;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="output_table")
public class Result {

	@Id	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="total_risk_capped_score")
	private int totalRiskCappedScore;
	
	@ElementCollection
	@CollectionTable(name="output_value", joinColumns = @JoinColumn(name="company_name"))
	@Column(name="output_values")
	private List<OutputValues> values;

}