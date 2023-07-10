package com.nagarro.calculator.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.calculator.enums.JobStatus;
import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.models.Job;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.repositories.CompanyRiskScoreRepository;
import com.nagarro.calculator.repositories.JobRepository;
import com.nagarro.calculator.repositories.RiskDimensionRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RiskDimensionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RiskDimensionServiceImplTest {
    @MockBean
    private CompanyRiskScoreRepository companyRiskScoreRepository;

    @MockBean
    private JobRepository jobRepository;

    @MockBean
    private RiskDimensionRepository riskDimensionRepository;

    @Autowired
    private RiskDimensionServiceImpl riskDimensionServiceImpl;

    /**
     * Method under test: {@link RiskDimensionServiceImpl#getAllRiskDimension()}
     */
    @Test
    void testGetAllRiskDimension() {
        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        when(riskDimensionRepository.findAll()).thenReturn(riskDimensionList);
        List<RiskDimension> actualAllRiskDimension = riskDimensionServiceImpl.getAllRiskDimension();
        assertSame(riskDimensionList, actualAllRiskDimension);
        assertTrue(actualAllRiskDimension.isEmpty());
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#saveRiskDimension(RiskDimension)}
     */
    @Test
    void testSaveRiskDimension() throws IOException {
        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        assertThrows(IOException.class, () -> riskDimensionServiceImpl.saveRiskDimension(riskDimension));
        verify(jobRepository).save(Mockito.<Job>any());
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#saveRiskDimension(RiskDimension)}
     */
    @Test
    void testSaveRiskDimension2() throws IOException {
        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("start : saveRiskDimension");
        riskDimension.setWeight(3);

        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        riskDimensionList.add(riskDimension);
        when(riskDimensionRepository.findAll()).thenReturn(riskDimensionList);

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("Dimension");
        riskDimension2.setWeight(3);
        assertThrows(IOException.class, () -> riskDimensionServiceImpl.saveRiskDimension(riskDimension2));
        verify(jobRepository).save(Mockito.<Job>any());
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#saveRiskDimension(RiskDimension)}
     */
    @Test
    void testSaveRiskDimension3() throws IOException {
        when(companyRiskScoreRepository.findAll()).thenReturn(new ArrayList<>());

        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        when(riskDimensionRepository.save(Mockito.<RiskDimension>any())).thenReturn(riskDimension);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("Dimension");
        riskDimension2.setWeight(100);
        assertSame(riskDimension, riskDimensionServiceImpl.saveRiskDimension(riskDimension2));
        verify(companyRiskScoreRepository).findAll();
        verify(riskDimensionRepository).save(Mockito.<RiskDimension>any());
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#saveRiskDimension(RiskDimension)}
     */
    @Test
    void testSaveRiskDimension4() throws IOException {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("start : saveRiskDimension");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);

        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        when(riskDimensionRepository.save(Mockito.<RiskDimension>any())).thenReturn(riskDimension);
        when(riskDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("Dimension");
        riskDimension2.setWeight(100);
        assertSame(riskDimension, riskDimensionServiceImpl.saveRiskDimension(riskDimension2));
        verify(companyRiskScoreRepository).findAll();
        verify(riskDimensionRepository).save(Mockito.<RiskDimension>any());
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#saveRiskDimension(RiskDimension)}
     */
    @Test
    void testSaveRiskDimension5() throws IOException {
        when(companyRiskScoreRepository.findAll()).thenReturn(new ArrayList<>());

        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("com.nagarro.calculator.models.RiskDimension");
        riskDimension.setWeight(1);

        ArrayList<RiskDimension> riskDimensionList = new ArrayList<>();
        riskDimensionList.add(riskDimension);

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("Dimension");
        riskDimension2.setWeight(3);
        when(riskDimensionRepository.save(Mockito.<RiskDimension>any())).thenReturn(riskDimension2);
        when(riskDimensionRepository.findAll()).thenReturn(riskDimensionList);

        RiskDimension riskDimension3 = new RiskDimension();
        riskDimension3.setDimension("Dimension");
        riskDimension3.setWeight(100);
        assertThrows(IOException.class, () -> riskDimensionServiceImpl.saveRiskDimension(riskDimension3));
        verify(jobRepository).save(Mockito.<Job>any());
        verify(riskDimensionRepository).findAll();
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#getRiskDimensionById(String)}
     */
    @Test
    void testGetRiskDimensionById() throws IOException {
        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        when(riskDimensionRepository.findByDimension(Mockito.<String>any())).thenReturn(riskDimension);
        assertSame(riskDimension, riskDimensionServiceImpl.getRiskDimensionById("Dimension"));
        verify(riskDimensionRepository).findByDimension(Mockito.<String>any());
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#deleteRiskDimension(RiskDimension)}
     */
    @Test
    void testDeleteRiskDimension() {
        doNothing().when(riskDimensionRepository).deleteById(Mockito.<String>any());

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        riskDimensionServiceImpl.deleteRiskDimension(riskDimension);
        verify(riskDimensionRepository).deleteById(Mockito.<String>any());
        assertEquals("Dimension", riskDimension.getDimension());
        assertEquals(3, riskDimension.getWeight());
        assertTrue(riskDimensionServiceImpl.getAllRiskDimension().isEmpty());
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#addDimensionToCompanyRiskScore(RiskDimension)}
     */
    @Test
    void testAddDimensionToCompanyRiskScore() {
        when(companyRiskScoreRepository.findAll()).thenReturn(new ArrayList<>());

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        riskDimensionServiceImpl.addDimensionToCompanyRiskScore(riskDimension);
        verify(companyRiskScoreRepository).findAll();
        assertEquals("Dimension", riskDimension.getDimension());
        assertEquals(3, riskDimension.getWeight());
        assertTrue(riskDimensionServiceImpl.getAllRiskDimension().isEmpty());
    }

    /**
     * Method under test: {@link RiskDimensionServiceImpl#addDimensionToCompanyRiskScore(RiskDimension)}
     */
    @Test
    void testAddDimensionToCompanyRiskScore2() {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        ArrayList<CompanyRiskScore> companyRiskScoreList = new ArrayList<>();
        companyRiskScoreList.add(companyRiskScore);
        when(companyRiskScoreRepository.findAll()).thenReturn(companyRiskScoreList);

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        riskDimensionServiceImpl.addDimensionToCompanyRiskScore(riskDimension);
        verify(companyRiskScoreRepository).findAll();
    }
}

