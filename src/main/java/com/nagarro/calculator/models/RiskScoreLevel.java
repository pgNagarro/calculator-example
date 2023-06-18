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
@Table(name="risk_score_level")
public class RiskScoreLevel {

	@Id
	@Column(name="score")
	private String score;
	
	@Column(name="level")
	private String level;
}
