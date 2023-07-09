package com.nagarro.calculator.services;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.repositories.RiskDimensionRepository;
import com.nagarro.calculator.services.impl.RiskDimensionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskDimensionServiceTest {

	@Autowired
	private RiskDimensionServiceImpl riskDimensionServiceTest;
	
	@MockBean
	private RiskDimensionRepository riskDimensionRepository;
	
	/**
	 * Method to test getAllRiskDimension
	 */
	@Test
	@DisplayName("Test getAllRiskDimension")
	public void getAllRiskDimensionTest() {
		
		when(riskDimensionRepository.findAll())
		.thenReturn(Arrays.asList(new RiskDimension("Conduct",47),
												new RiskDimension("Resilence",13)));
		
		assertEquals(2,riskDimensionServiceTest.getAllRiskDimension().size());
	
	}
	
	/**
	 * Method to test saveRiskDimension
	 * @throws IOException 
	 */
	@Test
	@DisplayName("Test saveRiskScoreLevel")
	public void saveRiskScoreLevelThrows() throws IOException {
		
		RiskDimension riskDimension = new RiskDimension("Conduct",100);
		
		when(riskDimensionRepository.save(riskDimension))
		.thenReturn(riskDimension);
		
		assertEquals(riskDimension,riskDimensionServiceTest.saveRiskDimension(riskDimension));
	}
	
	/**
	 * Method to test saveRiskDimension
	 * @throws IOException 
	 */
	@Test
	@DisplayName("Test saveRiskScoreLevel if throws exception")
	public void shouldThrowIfsaveRiskScoreLevelThrows() throws IOException {
		
		RiskDimension riskDimension = new RiskDimension("Conduct",10);
		
		when(riskDimensionRepository.save(riskDimension))
		.thenReturn(riskDimension);
		
		assertThrows(IOException.class, ()->riskDimensionServiceTest.saveRiskDimension(riskDimension));
	}
	
	/**
	 * Method to test getRiskDimensionByDimension
	 * @throws IOException 
	 */
	@Test
	@DisplayName("Test getRiskDimensionByDimension")
	public void getRiskScoreLevelByScoreTest() throws IOException {
		
		String  dimension= "Resilience";
		RiskDimension riskDimension =  new RiskDimension("Conduct",47);
		
		when(riskDimensionRepository.findByDimension(dimension))
		.thenReturn(riskDimension);
		
		assertEquals(riskDimension, riskDimensionServiceTest.getRiskDimensionById(dimension));
	}
	
	/**
	 * Method to test deleteRiskDimension
	 */
	@Test
	@DisplayName("Test deleteRiskDimension")
	public void deleteRiskDimensionTest() {
		
		RiskDimension riskDimension =  new RiskDimension("Conduct",47);
		
		riskDimensionServiceTest.deleteRiskDimension(riskDimension);
		
		verify(riskDimensionRepository,times(1)).deleteById(riskDimension.getDimension());
		
	}
	
	

}
