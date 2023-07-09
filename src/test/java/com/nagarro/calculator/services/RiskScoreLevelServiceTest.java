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

import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.repositories.RiskScoreLevelRepository;
import com.nagarro.calculator.services.impl.RiskScoreLevelServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskScoreLevelServiceTest {

	@Autowired 
	private RiskScoreLevelServiceImpl riskScoreLevelService;
	
	@MockBean
	private RiskScoreLevelRepository riskScoreLevelRepository;
	
	/**
	 * Method to test getAllRiskScoreLevel
	 */
	@Test
	@DisplayName("Test getAllRiskScoreLevel")
	public void getAllRiskScoreLevelTest() {
		
		when(riskScoreLevelRepository.findAll())
		.thenReturn(Arrays.asList(new RiskScoreLevel("81-100","Very low risk"),
												new RiskScoreLevel("61-80","Low risk")));
		
		assertEquals(2,riskScoreLevelService.getAllRiskScoreLevel().size());
	
	}
	
	/**
	 * Method to test saveRiskScoreLevel
	 */
	@Test
	@DisplayName("Test saveRiskScoreLevel")
	public void saveRiskScoreLevelTest() {
		
		RiskScoreLevel riskScoreLevel = new RiskScoreLevel("81-100","Very low risk");
		
		when(riskScoreLevelRepository.save(riskScoreLevel))
		.thenReturn(riskScoreLevel);
		
		assertEquals(riskScoreLevel, riskScoreLevelService.saveRiskScoreLevel(riskScoreLevel));
	}
	
	/**
	 * Method to test getRiskScoreLevelByScore
	 * @throws IOException 
	 */
	@Test
	@DisplayName("Test getRiskScoreLevelByScore")
	public void getRiskScoreLevelByScoreTest() throws IOException {
		
		String score = "70-80";
		RiskScoreLevel riskScoreLevel = new RiskScoreLevel("81-100","Very low risk");
		
		when(riskScoreLevelRepository.findByScore(score))
		.thenReturn(riskScoreLevel);
		
		assertEquals(riskScoreLevel, riskScoreLevelService.getRiskScoreLevelByScore(score));
	}
	
	/**
	 * Method to test deleteRiskScoreLevel
	 */
	@Test
	@DisplayName("Test deleteRiskScoreLevel")
	public void deleteRiskScoreLevelTest() {
		
		RiskScoreLevel riskScoreLevel = new RiskScoreLevel("81-100","Very low risk");
		riskScoreLevelService.deleteRiskScoreLevel(riskScoreLevel);
		
		verify(riskScoreLevelRepository,times(1)).deleteById(riskScoreLevel.getScore());
	}
	

}
