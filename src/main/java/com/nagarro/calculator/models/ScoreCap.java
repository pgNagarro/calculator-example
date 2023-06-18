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
@Table(name="score_cap")
public class ScoreCap {
	
	@Id
	@Column(name="conditions")
	private String condition;
	
	@Column(name="total_risk_capped_score")
	private String totalRiskCappedScore;
}
