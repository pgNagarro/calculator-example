package com.nagarro.calculator.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.models.Job;
import com.nagarro.calculator.models.OutputValues;
import com.nagarro.calculator.models.Result;
import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.repositories.ResultRepository;
import com.nagarro.calculator.services.CompanyRiskScoreService;
import com.nagarro.calculator.services.JobService;
import com.nagarro.calculator.services.RiskCalcService;
import com.nagarro.calculator.services.RiskDimensionService;
import com.nagarro.calculator.services.RiskScoreLevelService;
import com.nagarro.calculator.services.ScoreCapService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ResultServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ResultServiceImplTest {
    @MockBean
    private CompanyRiskScoreService companyRiskScoreService;

    @MockBean
    private JobService jobService;

    @MockBean
    private ResultRepository resultRepository;

    @Autowired
    private ResultServiceImpl resultServiceImpl;

    @MockBean
    private RiskCalcService riskCalcService;

    @MockBean
    private RiskDimensionService riskDimensionService;

    @MockBean
    private RiskScoreLevelService riskScoreLevelService;

    @MockBean
    private ScoreCapService scoreCapService;

    /**
     * Method under test: {@link ResultServiceImpl#addResult(Result)}
     */
    @Test
    void testAddResult() {
        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(3);
        ArrayList<OutputValues> values = new ArrayList<>();
        result.setValues(values);
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);

        Result result2 = new Result();
        result2.setCompanyName("Company Name");
        result2.setTotalRiskCappedScore(3);
        result2.setValues(new ArrayList<>());
        resultServiceImpl.addResult(result2);
        verify(resultRepository).save(Mockito.<Result>any());
        assertEquals("Company Name", result2.getCompanyName());
        assertEquals(values, result2.getValues());
        assertEquals(3, result2.getTotalRiskCappedScore());
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#getResult()}
     */
    @Test
    void testGetResult() {
        ArrayList<Result> resultList = new ArrayList<>();
        when(resultRepository.findAll()).thenReturn(resultList);
        List<Result> actualResult = resultServiceImpl.getResult();
        assertSame(resultList, actualResult);
        assertTrue(actualResult.isEmpty());
        verify(resultRepository).findAll();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult() {
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(new ArrayList<>());
        doNothing().when(jobService).addJob(Mockito.<Job>any());
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(new ArrayList<>());
        when(riskDimensionService.getAllRiskDimension()).thenReturn(new ArrayList<>());
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        resultServiceImpl.calculateResult();
        verify(companyRiskScoreService, atLeast(1)).getAllCompanyRiskScore();
        verify(jobService).addJob(Mockito.<Job>any());
        verify(riskCalcService).getAllRiskCalcLogic();
        verify(riskDimensionService).getAllRiskDimension();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult2() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateResult");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        doNothing().when(jobService).addJob(Mockito.<Job>any());
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(new ArrayList<>());
        when(riskDimensionService.getAllRiskDimension()).thenReturn(new ArrayList<>());
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        resultServiceImpl.calculateResult();
        verify(companyRiskScoreService, atLeast(1)).getAllCompanyRiskScore();
        verify(jobService).addJob(Mockito.<Job>any());
        verify(riskCalcService).getAllRiskCalcLogic();
        verify(riskDimensionService).getAllRiskDimension();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult3() {
        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("start : calculateResult");
        dimensions.setDimensionValue(42);

        ArrayList<Dimensions> dimensions2 = new ArrayList<>();
        dimensions2.add(dimensions);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateResult");
        companyRiskScore.setDimensions(dimensions2);
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        doNothing().when(jobService).addJob(Mockito.<Job>any());
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(new ArrayList<>());
        when(riskDimensionService.getAllRiskDimension()).thenReturn(new ArrayList<>());
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        resultServiceImpl.calculateResult();
        verify(companyRiskScoreService, atLeast(1)).getAllCompanyRiskScore();
        verify(jobService).addJob(Mockito.<Job>any());
        verify(riskCalcService).getAllRiskCalcLogic();
        verify(riskDimensionService).getAllRiskDimension();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult4() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateResult");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        doNothing().when(jobService).addJob(Mockito.<Job>any());

        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("start : calculateTotalRiskedCappedScore");
        riskCalc.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(riskCalcList);
        when(riskDimensionService.getAllRiskDimension()).thenReturn(new ArrayList<>());
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        resultServiceImpl.calculateResult();
        verify(companyRiskScoreService, atLeast(1)).getAllCompanyRiskScore();
        verify(jobService).addJob(Mockito.<Job>any());
        verify(riskCalcService).getAllRiskCalcLogic();
        verify(riskDimensionService).getAllRiskDimension();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult5() {
        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("start : calculateResult");
        dimensions.setDimensionValue(42);

        ArrayList<Dimensions> dimensions2 = new ArrayList<>();
        dimensions2.add(dimensions);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateResult");
        companyRiskScore.setDimensions(dimensions2);
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        doNothing().when(jobService).addJob(Mockito.<Job>any());
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(new ArrayList<>());
        when(riskDimensionService.getAllRiskDimension()).thenReturn(new ArrayList<>());

        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("zero");
        riskScoreLevel.setScore("zero");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(riskScoreLevelList);
        resultServiceImpl.calculateResult();
        verify(companyRiskScoreService, atLeast(1)).getAllCompanyRiskScore();
        verify(jobService).addJob(Mockito.<Job>any());
        verify(riskCalcService).getAllRiskCalcLogic();
        verify(riskDimensionService).getAllRiskDimension();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult6() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateResult");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        doNothing().when(jobService).addJob(Mockito.<Job>any());

        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("start : calculateTotalRiskedCappedScore");
        riskCalc.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(riskCalcList);

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("start : calculateResult");
        riskDimension.setWeight(3);

        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        riskDimensionList.add(riskDimension);
        when(riskDimensionService.getAllRiskDimension()).thenReturn(riskDimensionList);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        resultServiceImpl.calculateResult();
        verify(companyRiskScoreService, atLeast(1)).getAllCompanyRiskScore();
        verify(jobService).addJob(Mockito.<Job>any());
        verify(riskCalcService).getAllRiskCalcLogic();
        verify(riskDimensionService).getAllRiskDimension();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult7() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateResult");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        doNothing().when(jobService).addJob(Mockito.<Job>any());

        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("start : calculateTotalRiskedCappedScore");
        riskCalc.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(riskCalcList);

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("start : calculateResult");
        riskDimension.setWeight(3);

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("start : calculateTotalRiskedCappedScore");
        riskDimension2.setWeight(10);

        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        riskDimensionList.add(riskDimension2);
        riskDimensionList.add(riskDimension);
        when(riskDimensionService.getAllRiskDimension()).thenReturn(riskDimensionList);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        resultServiceImpl.calculateResult();
        verify(companyRiskScoreService, atLeast(1)).getAllCompanyRiskScore();
        verify(jobService).addJob(Mockito.<Job>any());
        verify(riskCalcService).getAllRiskCalcLogic();
        verify(riskDimensionService).getAllRiskDimension();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula() throws IOException {
        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations",
                1);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEvaluateFormula2() throws IOException {

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();

        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations",
                1);
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEvaluateFormula3() throws IOException {

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();

        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("min");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations",
                1);
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEvaluateFormula4() throws IOException {

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();

        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("max");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations",
                1);
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEvaluateFormula5() throws IOException {

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        RiskCalc riskCalc = mock(RiskCalc.class);
        when(riskCalc.getElementName()).thenReturn("Element Name");
        when(riskCalc.getFormula()).thenReturn("Formula");
        doNothing().when(riskCalc).setElementName(Mockito.<String>any());
        doNothing().when(riskCalc).setFormula(Mockito.<String>any());
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations",
                1);
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula6() throws IOException {
        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        RiskCalc riskCalc = mock(RiskCalc.class);
        when(riskCalc.getElementName()).thenReturn("Element Name");
        when(riskCalc.getFormula()).thenReturn("Formula");
        doNothing().when(riskCalc).setElementName(Mockito.<String>any());
        doNothing().when(riskCalc).setFormula(Mockito.<String>any());
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();

        HashMap<String, Integer> elementResultMap = new HashMap<>();
        elementResultMap.put("Formula", -1);
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, elementResultMap, "Operations",
                1);
        verify(riskCalc).getElementName();
        verify(riskCalc).getFormula();
        verify(riskCalc).setElementName(Mockito.<String>any());
        verify(riskCalc).setFormula(Mockito.<String>any());
        assertEquals(2, elementResultMap.size());
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEvaluateFormula7() throws IOException {
       

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        RiskCalc riskCalc = mock(RiskCalc.class);
        when(riskCalc.getElementName()).thenReturn("Element Name");
        when(riskCalc.getFormula()).thenReturn("Formula");
        doNothing().when(riskCalc).setElementName(Mockito.<String>any());
        doNothing().when(riskCalc).setFormula(Mockito.<String>any());
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Formula", 1);
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEvaluateFormula8() throws IOException {
        

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("max");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(3);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("42");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(3);

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyRiskScore2);
        riskScoreList.add(companyRiskScore);
        RiskCalc riskCalc = mock(RiskCalc.class);
        when(riskCalc.getElementName()).thenReturn("Element Name");
        when(riskCalc.getFormula()).thenReturn("Formula");
        doNothing().when(riskCalc).setElementName(Mockito.<String>any());
        doNothing().when(riskCalc).setFormula(Mockito.<String>any());
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");

        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        riskCalcList.add(riskCalc);
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations",
                1);
    }

    /**
     * Method under test: {@link ResultServiceImpl#insertValuesInResultTable(Map, List, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInsertValuesInResultTable() {

        HashMap<String, Integer> elementResultMap = new HashMap<>();
        resultServiceImpl.insertValuesInResultTable(elementResultMap, new ArrayList<>(), 3);
    }

    /**
     * Method under test: {@link ResultServiceImpl#insertValuesInResultTable(Map, List, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInsertValuesInResultTable2() {
        

        HashMap<String, Integer> elementResultMap = new HashMap<>();
        CompanyRiskScore companyRiskScore = mock(CompanyRiskScore.class);
        doNothing().when(companyRiskScore).setCompanyName(Mockito.<String>any());
        doNothing().when(companyRiskScore).setDimensions(Mockito.<List<Dimensions>>any());
        doNothing().when(companyRiskScore).setId(anyInt());
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyRiskScore);
        resultServiceImpl.insertValuesInResultTable(elementResultMap, riskScoreList, 3);
    }

    /**
     * Method under test: {@link ResultServiceImpl#insertValuesInResultTable(Map, List, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInsertValuesInResultTable3() {
        
        HashMap<String, Integer> elementResultMap = new HashMap<>();
        CompanyRiskScore companyRiskScore = mock(CompanyRiskScore.class);
        when(companyRiskScore.getCompanyName()).thenReturn("Company Name");
        doNothing().when(companyRiskScore).setCompanyName(Mockito.<String>any());
        doNothing().when(companyRiskScore).setDimensions(Mockito.<List<Dimensions>>any());
        doNothing().when(companyRiskScore).setId(anyInt());
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyRiskScore);
        resultServiceImpl.insertValuesInResultTable(elementResultMap, riskScoreList, 0);
    }

    /**
     * Method under test: {@link ResultServiceImpl#insertValuesInResultTable(Map, List, int)}
     */
    @Test
    void testInsertValuesInResultTable4() {
        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(3);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);

        HashMap<String, Integer> elementResultMap = new HashMap<>();
        elementResultMap.put("total_risk_capped_score", 1);
        CompanyRiskScore companyRiskScore = mock(CompanyRiskScore.class);
        when(companyRiskScore.getCompanyName()).thenReturn("Company Name");
        doNothing().when(companyRiskScore).setCompanyName(Mockito.<String>any());
        doNothing().when(companyRiskScore).setDimensions(Mockito.<List<Dimensions>>any());
        doNothing().when(companyRiskScore).setId(anyInt());
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyRiskScore);
        resultServiceImpl.insertValuesInResultTable(elementResultMap, riskScoreList, 0);
        verify(resultRepository).save(Mockito.<Result>any());
        verify(companyRiskScore).getCompanyName();
        verify(companyRiskScore).setCompanyName(Mockito.<String>any());
        verify(companyRiskScore).setDimensions(Mockito.<List<Dimensions>>any());
        verify(companyRiskScore).setId(anyInt());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    void testCalculateTotalRiskedCappedScore() throws IOException {
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(new ArrayList<>());
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        assertTrue(resultServiceImpl.calculateTotalRiskedCappedScore().isEmpty());
        verify(companyRiskScoreService).getAllCompanyRiskScore();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    void testCalculateTotalRiskedCappedScore2() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateTotalRiskedCappedScore");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        Map<String, Integer> actualCalculateTotalRiskedCappedScoreResult = resultServiceImpl
                .calculateTotalRiskedCappedScore();
        assertEquals(1, actualCalculateTotalRiskedCappedScoreResult.size());
        Integer expectedGetResult = new Integer(100);
        assertEquals(expectedGetResult,
                actualCalculateTotalRiskedCappedScoreResult.get("start : calculateTotalRiskedCappedScore"));
        verify(companyRiskScoreService).getAllCompanyRiskScore();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    void testCalculateTotalRiskedCappedScore3() throws IOException {
        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("start : calculateTotalRiskedCappedScore");
        dimensions.setDimensionValue(42);

        ArrayList<Dimensions> dimensions2 = new ArrayList<>();
        dimensions2.add(dimensions);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateTotalRiskedCappedScore");
        companyRiskScore.setDimensions(dimensions2);
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        Map<String, Integer> actualCalculateTotalRiskedCappedScoreResult = resultServiceImpl
                .calculateTotalRiskedCappedScore();
        assertEquals(1, actualCalculateTotalRiskedCappedScoreResult.size());
        Integer expectedGetResult = new Integer(100);
        assertEquals(expectedGetResult,
                actualCalculateTotalRiskedCappedScoreResult.get("start : calculateTotalRiskedCappedScore"));
        verify(companyRiskScoreService).getAllCompanyRiskScore();
        verify(riskScoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateTotalRiskedCappedScore4() throws IOException {
       

        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("start : calculateTotalRiskedCappedScore");
        dimensions.setDimensionValue(42);

        ArrayList<Dimensions> dimensions2 = new ArrayList<>();
        dimensions2.add(dimensions);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateTotalRiskedCappedScore");
        companyRiskScore.setDimensions(dimensions2);
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);

        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("One");
        riskScoreLevel.setScore("One");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(riskScoreLevelList);
        resultServiceImpl.calculateTotalRiskedCappedScore();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateTotalRiskedCappedScore5() throws IOException {
       

        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("start : calculateTotalRiskedCappedScore");
        dimensions.setDimensionValue(42);

        ArrayList<Dimensions> dimensions2 = new ArrayList<>();
        dimensions2.add(dimensions);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateTotalRiskedCappedScore");
        companyRiskScore.setDimensions(dimensions2);
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        RiskScoreLevel riskScoreLevel = mock(RiskScoreLevel.class);
        when(riskScoreLevel.getScore()).thenReturn("42");
        doNothing().when(riskScoreLevel).setLevel(Mockito.<String>any());
        doNothing().when(riskScoreLevel).setScore(Mockito.<String>any());
        riskScoreLevel.setLevel("One");
        riskScoreLevel.setScore("One");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(riskScoreLevelList);
        resultServiceImpl.calculateTotalRiskedCappedScore();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateTotalRiskedCappedScore6() throws IOException {
      
        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("start : calculateTotalRiskedCappedScore");
        dimensions.setDimensionValue(42);

        Dimensions dimensions2 = new Dimensions();
        dimensions2.setDimensionName("zero");
        dimensions2.setDimensionValue(10);

        ArrayList<Dimensions> dimensions3 = new ArrayList<>();
        dimensions3.add(dimensions2);
        dimensions3.add(dimensions);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : calculateTotalRiskedCappedScore");
        companyRiskScore.setDimensions(dimensions3);
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(companyRiskScoreList);
        RiskScoreLevel riskScoreLevel = mock(RiskScoreLevel.class);
        when(riskScoreLevel.getScore()).thenReturn("42");
        doNothing().when(riskScoreLevel).setLevel(Mockito.<String>any());
        doNothing().when(riskScoreLevel).setScore(Mockito.<String>any());
        riskScoreLevel.setLevel("One");
        riskScoreLevel.setScore("One");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(riskScoreLevelList);
        resultServiceImpl.calculateTotalRiskedCappedScore();
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore() {
        HashMap<String, Integer> level = new HashMap<>();
        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        resultServiceImpl.compareScore(level, riskScoreLevelList, new ArrayList<>(), 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore2() {
        HashMap<String, Integer> level = new HashMap<>();
        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();

        ArrayList<Integer> values = new ArrayList<>();
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCompareScore3() {
      
        HashMap<String, Integer> level = new HashMap<>();

        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("com.nagarro.calculator.models.RiskScoreLevel");
        riskScoreLevel.setScore("com.nagarro.calculator.models.RiskScoreLevel");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);

        ArrayList<Integer> values = new ArrayList<>();
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCompareScore4() {
        

        HashMap<String, Integer> level = new HashMap<>();

        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("com.nagarro.calculator.models.RiskScoreLevel");
        riskScoreLevel.setScore("-");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);

        ArrayList<Integer> values = new ArrayList<>();
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore5() {
        HashMap<String, Integer> level = new HashMap<>();

        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("com.nagarro.calculator.models.RiskScoreLevel");
        riskScoreLevel.setScore("42");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);

        ArrayList<Integer> values = new ArrayList<>();
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCompareScore6() {
   

        HashMap<String, Integer> level = new HashMap<>();
        RiskScoreLevel riskScoreLevel = mock(RiskScoreLevel.class);
        when(riskScoreLevel.getScore()).thenReturn("Score");
        doNothing().when(riskScoreLevel).setLevel(Mockito.<String>any());
        doNothing().when(riskScoreLevel).setScore(Mockito.<String>any());
        riskScoreLevel.setLevel("com.nagarro.calculator.models.RiskScoreLevel");
        riskScoreLevel.setScore("com.nagarro.calculator.models.RiskScoreLevel");

        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(riskScoreLevel);

        ArrayList<Integer> values = new ArrayList<>();
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
    }

    /**
     * Method under test: {@link ResultServiceImpl#addJobStatus()}
     */
    @Test
    void testAddJobStatus() {
        doNothing().when(jobService).addJob(Mockito.<Job>any());
        resultServiceImpl.addJobStatus();
        verify(jobService).addJob(Mockito.<Job>any());
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }
}

