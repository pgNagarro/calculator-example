package com.nagarro.calculator.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Dimensions
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensions {

	@Column(name="dimension")
	private String dimensionName;
	
	@Column(name="value")
	private int dimensionValue;
}