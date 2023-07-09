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

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.repositories.CompanyRiskScoreRepository;
import com.nagarro.calculator.repositories.ResultRepository;
import com.nagarro.calculator.services.impl.CompanyRiskScoreServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRiskScoreServiceTest {

	@Autowired
	private CompanyRiskScoreServiceImpl companyRiskScoreService;
	
	@MockBean
	private CompanyRiskScoreRepository companyRiskScoreRepository;
	
	@MockBean
	private ResultRepository resultRepository;
	
	/**
	 * Method to test getAllRiskScoreLevel
	 */
	@Test
	@DisplayName("Test getAllCompanyRiskScore")
	public void getAllCompanyRiskScoreTest() {
		
		when(companyRiskScoreRepository.findAll())
				.thenReturn(Arrays.asList(
						new CompanyRiskScore(1, "TCS",
								Arrays.asList(new Dimensions("Information Security", 80),
										new Dimensions("Resilence", 60), new Dimensions("Conduct", 70))),
						new CompanyRiskScore(2, "Infosys",
								Arrays.asList(new Dimensions("Information Security", 90),
										new Dimensions("Resilence", 50), new Dimensions("Conduct", 55))),
						new CompanyRiskScore(3, "CreditSuisse",
								Arrays.asList(new Dimensions("Information Security", 50),
										new Dimensions("Resilence", 40), new Dimensions("Conduct", 30)))));
		
		assertEquals(3,companyRiskScoreService.getAllCompanyRiskScore().size());
	
	}
	
	/**
	 * Method to test saveRiskScore
	 * @throws IOException 
	 */
	@Test
	@DisplayName("Test saveRiskScore")
	public void saveRiskScoreTest() throws IOException {
		
		CompanyRiskScore companyRiskScore = new CompanyRiskScore(1, "TCS",
				Arrays.asList(new Dimensions("Information Security", 80),
						new Dimensions("Resilence", 60), new Dimensions("Conduct", 70)));
		
		companyRiskScoreService.saveRiskScore(companyRiskScore);
		
		verify(companyRiskScoreRepository,times(1)).save(companyRiskScore);
	}
	
	/**
	 * Method to test getRiskScoreByCompanyName
	 * @throws IOException 
	 */
	@Test
	@DisplayName("Test getRiskScoreByCompanyName")
	public void getRiskScoreByCompanyName() throws IOException {
		
		String companyName = "TCS";
		CompanyRiskScore companyRiskScore = new CompanyRiskScore(1, "TCS",
				Arrays.asList(new Dimensions("Information Security", 80),
						new Dimensions("Resilence", 60), new Dimensions("Conduct", 70)));
		
		when(companyRiskScoreRepository.findByCompanyName(companyName))
		.thenReturn(Arrays.asList(
				new CompanyRiskScore(1, "TCS",
						Arrays.asList(new Dimensions("Information Security", 80),
								new Dimensions("Resilence", 60), new Dimensions("Conduct", 70))),
				new CompanyRiskScore(2, "Infosys",
						Arrays.asList(new Dimensions("Information Security", 90),
								new Dimensions("Resilence", 50), new Dimensions("Conduct", 55))),
				new CompanyRiskScore(3, "CreditSuisse",
						Arrays.asList(new Dimensions("Information Security", 50),
								new Dimensions("Resilence", 40), new Dimensions("Conduct", 30)))));
		
		assertEquals(companyRiskScore, companyRiskScoreService.getCompanyRiskScoreByCompanyName(companyName));
	}
	
	/**
	 * Method to test deleteCompanyRiskScore
	 */
	@Test
	@DisplayName("Test deleteCompanyRiskScore")
	public void deleteCompanyRiskScoreTest() {
		
		CompanyRiskScore companyRiskScore = new CompanyRiskScore(1, "TCS",
				Arrays.asList(new Dimensions("Information Security", 80),
						new Dimensions("Resilence", 60), new Dimensions("Conduct", 70)));
		
		companyRiskScoreService.deleteCompanyRiskScore(companyRiskScore);
		
		verify(companyRiskScoreRepository,times(1)).deleteById(companyRiskScore.getId());
		verify(resultRepository,times(1)).deleteById(companyRiskScore.getCompanyName());
	}
	
	
	
}
