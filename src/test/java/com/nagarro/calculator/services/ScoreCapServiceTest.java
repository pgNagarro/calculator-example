package com.nagarro.calculator.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarro.calculator.models.ScoreCap;
import com.nagarro.calculator.repositories.ScoreCapRepository;
import com.nagarro.calculator.services.impl.ScoreCapServiceImpl;

/**
 * Test Class for score cap service
 * @author parasgautam
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreCapServiceTest {

	@Autowired
	private ScoreCapServiceImpl scoreCapServiceTest;
	
	@MockBean
	private ScoreCapRepository scoreCapRepository;
	
	/**
	 * Method to test getAllScoreCap 
	 */
	 @Test
	 @DisplayName("Test getAllScoreCap")
	 public void getAllScoreCapTest() {
		 
		 when(scoreCapRepository.findAll())
		 .thenReturn(Arrays.asList(new ScoreCap("One \"very high risk\"",30),
					new ScoreCap("Two \"very high risk\"",10),
					new ScoreCap("One \"high risk\"",40)));
		 
		 assertEquals(3,scoreCapServiceTest.getAllScoreCap().size());
		 
	 }
	 
	 /**
	  * Method to test saveScoreCap
	  */
	 @Test
	 @DisplayName("Test saveScoreCap")
	 public void saveScoreCapTest() {
		 
		 ScoreCap scoreCap = new ScoreCap("One \"very high risk\"",40);
		 
		 when(scoreCapRepository.save(scoreCap))
		 .thenReturn(scoreCap);
		 
		 assertEquals(scoreCap, scoreCapServiceTest.saveScoreCap(scoreCap));
		 
	 }
	 
	 /**
	  * Method to test getScoreCapByCondition
	  * @throws IOException
	  */
	 @Test
	 @DisplayName("Test getScoreCapByCondition")
	 public void getScoreCapByConditionTest() throws IOException {
		 
		 String condition = "\"Two \\\"very high risk\\\"\"";
		 ScoreCap scoreCap = new ScoreCap("One \"very high risk\"",40);
		 
		when(scoreCapRepository.findByCondition(condition))
		.thenReturn(scoreCap);
		
		assertEquals(scoreCap,scoreCapServiceTest.getScoreCapByCondition(condition));
		
	 }
	 
	 /**
	  * Method to test deleteScoreCap
	  */
	 @Test
	 @DisplayName("Test deleteScoreCap")
	 public void deleteScoreCapTest() {
		 
		 ScoreCap scoreCap = new ScoreCap("One \"very high risk\"",40);
		 scoreCapServiceTest.deleteScoreCap(scoreCap);
		 
		 verify(scoreCapRepository,times(1)).deleteById(scoreCap.getCondition());
		 
	 }
	
}
