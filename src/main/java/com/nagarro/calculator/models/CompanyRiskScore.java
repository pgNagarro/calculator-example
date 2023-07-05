package com.nagarro.calculator.models;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;



import lombok.*;

/**
 * Entity class for company risk score
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="company_risk_score")
public class CompanyRiskScore {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="company_name")
	private String companyName;

	@ElementCollection
	@CollectionTable(name="dimensions", joinColumns = @JoinColumn(name="company_name"))
	@Column(name="dimensions")
	private List<Dimensions> dimensions;


}
