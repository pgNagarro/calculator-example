package com.nagarro.calculator.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Dimensions;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.repositories.CompanyRiskScoreRepository;
import com.nagarro.calculator.repositories.ResultRepository;
import com.nagarro.calculator.repositories.RiskDimensionRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompanyRiskScoreServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CompanyRiskScoreServiceImplTest {
    @MockBean
    private CompanyRiskScoreRepository companyRiskScoreRepository;

    @Autowired
    private CompanyRiskScoreServiceImpl companyRiskScoreServiceImpl;

    @MockBean
    private ResultRepository resultRepository;

    @MockBean
    private RiskDimensionRepository riskDimensionRepository;

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#getAllCompanyRiskScore()}
     */
    @Test
    void testGetAllCompanyRiskScore() {
        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);
        List<CompanyRiskScore> actualAllCompanyRiskScore = companyRiskScoreServiceImpl.getAllCompanyRiskScore();
        assertSame(companyRiskScoreList, actualAllCompanyRiskScore);
        assertTrue(actualAllCompanyRiskScore.isEmpty());
        verify(companyRiskScoreRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#checkDataIfPresent(CompanyRiskScore)}
     */
    @Test
    void testCheckDataIfPresent() {
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        assertTrue(companyRiskScoreServiceImpl.checkDataIfPresent(companyRiskScore));
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#checkDataIfPresent(CompanyRiskScore)}
     */
    @Test
    void testCheckDataIfPresent2() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : checkDataIfPresent");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyRiskScoreList);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(1);
        assertFalse(companyRiskScoreServiceImpl.checkDataIfPresent(companyRiskScore2));
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        when(companyRiskScoreRepository.save(Mockito.<CompanyRiskScore>any())).thenReturn(companyRiskScore);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyRiskScoreRepository.findAll()).thenReturn(new ArrayList<>());
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore2);
        verify(companyRiskScoreRepository).save(Mockito.<CompanyRiskScore>any());
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore2() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : saveCompanyRiskScore");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyRiskScoreList);
        when(companyRiskScoreRepository.findAll()).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        ArrayList<Dimensions> dimensions = new ArrayList<>();
        companyRiskScore2.setDimensions(dimensions);
        companyRiskScore2.setId(1);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore3 = new CompanyRiskScore();
        companyRiskScore3.setCompanyName("Company Name");
        companyRiskScore3.setDimensions(new ArrayList<>());
        companyRiskScore3.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore3);
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
        assertEquals("Company Name", companyRiskScore3.getCompanyName());
        assertEquals(1, companyRiskScore3.getId());
        assertEquals(dimensions, companyRiskScore3.getDimensions());
        assertEquals(dimensions, companyRiskScoreServiceImpl.getAllCompanyRiskScore());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore3() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : saveCompanyRiskScore");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore2);
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore4() throws IOException {
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyRiskScoreRepository.findAll()).thenReturn(new ArrayList<>());

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("start : saveCompanyRiskScore");
        riskDimension.setWeight(3);

        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        riskDimensionList.add(riskDimension);
        when(riskDimensionRepository.findAll()).thenReturn(riskDimensionList);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore);
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore5() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : saveCompanyRiskScore");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(3);

        ArrayList<CompanyRiskScore> companyRiskScoreList2 = new ArrayList<>();
        companyRiskScoreList2.add(companyRiskScore2);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyRiskScoreList);
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList2);

        CompanyRiskScore companyRiskScore3 = new CompanyRiskScore();
        companyRiskScore3.setCompanyName("Company Name");
        ArrayList<Dimensions> dimensions = new ArrayList<>();
        companyRiskScore3.setDimensions(dimensions);
        companyRiskScore3.setId(1);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore4 = new CompanyRiskScore();
        companyRiskScore4.setCompanyName("Company Name");
        companyRiskScore4.setDimensions(new ArrayList<>());
        companyRiskScore4.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore4);
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
        assertEquals("Company Name", companyRiskScore4.getCompanyName());
        assertEquals(1, companyRiskScore4.getId());
        assertEquals(dimensions, companyRiskScore4.getDimensions());
        assertEquals(1, companyRiskScoreServiceImpl.getAllCompanyRiskScore().size());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore6() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : saveCompanyRiskScore");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(3);

        CompanyRiskScore companyRiskScore3 = new CompanyRiskScore();
        companyRiskScore3.setCompanyName("start : saveCompanyRiskScore");
        companyRiskScore3.setDimensions(new ArrayList<>());
        companyRiskScore3.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList2 = new ArrayList<>();
        companyRiskScoreList2.add(companyRiskScore3);
        companyRiskScoreList2.add(companyRiskScore2);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyRiskScoreList);
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList2);

        CompanyRiskScore companyRiskScore4 = new CompanyRiskScore();
        companyRiskScore4.setCompanyName("Company Name");
        ArrayList<Dimensions> dimensions = new ArrayList<>();
        companyRiskScore4.setDimensions(dimensions);
        companyRiskScore4.setId(1);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore5 = new CompanyRiskScore();
        companyRiskScore5.setCompanyName("Company Name");
        companyRiskScore5.setDimensions(new ArrayList<>());
        companyRiskScore5.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore5);
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
        assertEquals("Company Name", companyRiskScore5.getCompanyName());
        assertEquals(1, companyRiskScore5.getId());
        assertEquals(dimensions, companyRiskScore5.getDimensions());
        assertEquals(2, companyRiskScoreServiceImpl.getAllCompanyRiskScore().size());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore7() throws IOException {
        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("start : saveCompanyRiskScore");
        dimensions.setDimensionValue(42);

        ArrayList<Dimensions> dimensions2 = new ArrayList<>();
        dimensions2.add(dimensions);

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : saveCompanyRiskScore");
        companyRiskScore.setDimensions(dimensions2);
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore2);
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore8() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("start : saveCompanyRiskScore");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore2);
        when(companyRiskScoreRepository.save(Mockito.<CompanyRiskScore>any())).thenReturn(companyRiskScore);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        Dimensions dimensions = new Dimensions();
        dimensions.setDimensionName("Dimension Name");
        dimensions.setDimensionValue(1);

        ArrayList<Dimensions> dimensions2 = new ArrayList<>();
        dimensions2.add(dimensions);

        CompanyRiskScore companyRiskScore3 = new CompanyRiskScore();
        companyRiskScore3.setCompanyName("Company Name");
        companyRiskScore3.setDimensions(dimensions2);
        companyRiskScore3.setId(1);
        companyRiskScoreServiceImpl.saveRiskScore(companyRiskScore3);
        verify(companyRiskScoreRepository, atLeast(1)).save(Mockito.<CompanyRiskScore>any());
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
        verify(companyRiskScoreRepository).findAll();
        List<Dimensions> dimensions3 = companyRiskScoreServiceImpl.getAllCompanyRiskScore().get(0).getDimensions();
        assertEquals(1, dimensions3.size());
        Dimensions getResult = dimensions3.get(0);
        assertEquals("Dimension Name", getResult.getDimensionName());
        assertEquals(0, getResult.getDimensionValue());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#getCompanyRiskScoreByCompanyName(String)}
     */
    @Test
    void testGetCompanyRiskScoreByCompanyName() throws IOException {
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertThrows(IOException.class,
                () -> companyRiskScoreServiceImpl.getCompanyRiskScoreByCompanyName("Company Name"));
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#getCompanyRiskScoreByCompanyName(String)}
     */
    @Test
    void testGetCompanyRiskScoreByCompanyName2() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : getCompanyRiskScoreByName");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyRiskScoreList);
        assertSame(companyRiskScore, companyRiskScoreServiceImpl.getCompanyRiskScoreByCompanyName("Company Name"));
        verify(companyRiskScoreRepository).findByCompanyName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#updateRiskScore(CompanyRiskScore)}
     */
    @Test
    void testUpdateRiskScore() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        when(companyRiskScoreRepository.save(Mockito.<CompanyRiskScore>any())).thenReturn(companyRiskScore);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(1);
        assertSame(companyRiskScore, companyRiskScoreServiceImpl.updateRiskScore(companyRiskScore2));
        verify(companyRiskScoreRepository).save(Mockito.<CompanyRiskScore>any());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#deleteCompanyRiskScore(CompanyRiskScore)}
     */
    @Test
    void testDeleteCompanyRiskScore() {
        doNothing().when(companyRiskScoreRepository).deleteById(Mockito.<Integer>any());
        doNothing().when(resultRepository).deleteById(Mockito.<String>any());

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        companyRiskScoreServiceImpl.deleteCompanyRiskScore(companyRiskScore);
        verify(companyRiskScoreRepository).deleteById(Mockito.<Integer>any());
        verify(resultRepository).deleteById(Mockito.<String>any());
        assertEquals("Company Name", companyRiskScore.getCompanyName());
        assertEquals(1, companyRiskScore.getId());
        assertTrue(companyRiskScore.getDimensions().isEmpty());
        assertTrue(companyRiskScoreServiceImpl.getAllCompanyRiskScore().isEmpty());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#addDimensiontoRestCompanies(String, Dimensions)}
     */
    @Test
    void testAddDimensiontoRestCompanies() {
        when(companyRiskScoreRepository.findAll()).thenReturn(new ArrayList<>());

        Dimensions dimension = new Dimensions();
        dimension.setDimensionName("Dimension Name");
        dimension.setDimensionValue(42);
        companyRiskScoreServiceImpl.addDimensiontoRestCompanies("Company Name", dimension);
        verify(companyRiskScoreRepository).findAll();
        assertEquals("Dimension Name", dimension.getDimensionName());
        assertEquals(42, dimension.getDimensionValue());
        assertTrue(companyRiskScoreServiceImpl.getAllCompanyRiskScore().isEmpty());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#addDimensiontoRestCompanies(String, Dimensions)}
     */
    @Test
    void testAddDimensiontoRestCompanies2() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);

        Dimensions dimension = new Dimensions();
        dimension.setDimensionName("Dimension Name");
        dimension.setDimensionValue(42);
        companyRiskScoreServiceImpl.addDimensiontoRestCompanies("Company Name", dimension);
        verify(companyRiskScoreRepository).findAll();
        assertEquals("Dimension Name", dimension.getDimensionName());
        assertEquals(42, dimension.getDimensionValue());
        assertEquals(1, companyRiskScoreServiceImpl.getAllCompanyRiskScore().size());
    }

    /**
     * Method under test: {@link CompanyRiskScoreServiceImpl#addDimensiontoRestCompanies(String, Dimensions)}
     */
    @Test
    void testAddDimensiontoRestCompanies3() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("com.nagarro.calculator.models.CompanyRiskScore");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(2);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore2);
        companyRiskScoreList.add(companyRiskScore);

        CompanyRiskScore companyRiskScore3 = new CompanyRiskScore();
        companyRiskScore3.setCompanyName("Company Name");
        companyRiskScore3.setDimensions(new ArrayList<>());
        companyRiskScore3.setId(1);
        when(companyRiskScoreRepository.save(Mockito.<CompanyRiskScore>any())).thenReturn(companyRiskScore3);
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);

        Dimensions dimension = new Dimensions();
        dimension.setDimensionName("Dimension Name");
        dimension.setDimensionValue(42);
        companyRiskScoreServiceImpl.addDimensiontoRestCompanies("Company Name", dimension);
        verify(companyRiskScoreRepository).save(Mockito.<CompanyRiskScore>any());
        verify(companyRiskScoreRepository).findAll();
        List<Dimensions> dimensions = companyRiskScoreServiceImpl.getAllCompanyRiskScore().get(0).getDimensions();
        assertEquals(1, dimensions.size());
        Dimensions getResult = dimensions.get(0);
        assertEquals("Dimension Name", getResult.getDimensionName());
        assertEquals(0, getResult.getDimensionValue());
    }
}

