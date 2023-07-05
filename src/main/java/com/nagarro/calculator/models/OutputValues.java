package com.nagarro.calculator.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Output values
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OutputValues {

	@Column(name="elementName")
	private String elementName;
	
	@Column(name="value")
	private int elementValue;
	
}

