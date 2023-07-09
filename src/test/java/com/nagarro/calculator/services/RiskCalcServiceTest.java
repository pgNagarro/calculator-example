package com.nagarro.calculator.services;

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

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.repositories.RiskCalcRepository;
import com.nagarro.calculator.services.impl.RiskCalcServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskCalcServiceTest {

	@Autowired
	private RiskCalcServiceImpl riskCalcServiceTest;
	
	@MockBean
	private RiskCalcRepository riskCalcRepository;
	
	/**
	 * Method to test getAllRiskCalc
	 */
	@Test
	@DisplayName("Test getAllRiskCalc")
	public void getAllRiskCalcTest() {
		
		when(riskCalcRepository.findAll())
		.thenReturn(Arrays.asList(new RiskCalc("info_sec_weight","Information Security*weight"),
				new RiskCalc("resilence_weight","Resilence*weight")));
		
		assertEquals(2,riskCalcServiceTest.getAllRiskCalcLogic().size());
	
	}
	
	/**
	 * Method to test saveRiskCalc
	 */
	@Test
	@DisplayName("Test saveRiskCalc")
	public void saveRiskCalcTest() {
		
		RiskCalc riskCalc = new RiskCalc("info_sec_weight","Information Security*weight");
		
		when(riskCalcRepository.save(riskCalc))
		.thenReturn(riskCalc);
		
		assertEquals(riskCalc, riskCalcServiceTest.saveRiskCalc(riskCalc));
		
	}
	
	/**
	 * Method to test getRiskCalcByName
	 * @throws IOException 
	 */
	@Test
	@DisplayName("Test getRiskCalcByName")
	public void getRiskCalcByName() throws IOException {
		
		String elementName = "info_sec_weight";
		RiskCalc riskCalc = new RiskCalc("info_sec_weight","Information Security*weight");
		
		when(riskCalcRepository.findByElementName(elementName))
		.thenReturn(riskCalc);
		
		assertEquals(riskCalc, riskCalcServiceTest.getRiskCalcLogicByName(elementName));
	}
	
	/**
	 * Method to test deleteRiskCalc
	 */
	@Test
	@DisplayName("Test deleteRiskCalc")
	public void deleteRiskCalcTest() {
		
		RiskCalc riskCalc = new RiskCalc("info_sec_weight","Information Security*weight");
		
		riskCalcServiceTest.deleteRiskCalc(riskCalc);
		
		verify(riskCalcRepository,times(1)).deleteById(riskCalc.getElementName());
	}
	

}
