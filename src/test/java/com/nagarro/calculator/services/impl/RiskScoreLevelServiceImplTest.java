package com.nagarro.calculator.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.repositories.RiskScoreLevelRepository;

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

@ContextConfiguration(classes = {RiskScoreLevelServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RiskScoreLevelServiceImplTest {
    @MockBean
    private RiskScoreLevelRepository riskScoreLevelRepository;

    @Autowired
    private RiskScoreLevelServiceImpl riskScoreLevelServiceImpl;

    /**
     * Method under test: {@link RiskScoreLevelServiceImpl#getAllRiskScoreLevel()}
     */
    @Test
    void testGetAllRiskScoreLevel() {
        ArrayList<RiskScoreLevel> riskScoreLevelList = new ArrayList<>();
        when(riskScoreLevelRepository.findAll()).thenReturn(riskScoreLevelList);
        List<RiskScoreLevel> actualAllRiskScoreLevel = riskScoreLevelServiceImpl.getAllRiskScoreLevel();
        assertSame(riskScoreLevelList, actualAllRiskScoreLevel);
        assertTrue(actualAllRiskScoreLevel.isEmpty());
        verify(riskScoreLevelRepository).findAll();
    }

    /**
     * Method under test: {@link RiskScoreLevelServiceImpl#saveRiskScoreLevel(RiskScoreLevel)}
     */
    @Test
    void testSaveRiskScoreLevel() {
        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("Level");
        riskScoreLevel.setScore("Score");
        when(riskScoreLevelRepository.save(Mockito.<RiskScoreLevel>any())).thenReturn(riskScoreLevel);

        RiskScoreLevel riskScoreLevel2 = new RiskScoreLevel();
        riskScoreLevel2.setLevel("Level");
        riskScoreLevel2.setScore("Score");
        assertSame(riskScoreLevel, riskScoreLevelServiceImpl.saveRiskScoreLevel(riskScoreLevel2));
        verify(riskScoreLevelRepository).save(Mockito.<RiskScoreLevel>any());
    }

    /**
     * Method under test: {@link RiskScoreLevelServiceImpl#getRiskScoreLevelByScore(String)}
     */
    @Test
    void testGetRiskScoreLevelByScore() throws IOException {
        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("Level");
        riskScoreLevel.setScore("Score");
        when(riskScoreLevelRepository.findByScore(Mockito.<String>any())).thenReturn(riskScoreLevel);
        assertSame(riskScoreLevel, riskScoreLevelServiceImpl.getRiskScoreLevelByScore("Score"));
        verify(riskScoreLevelRepository).findByScore(Mockito.<String>any());
    }

    /**
     * Method under test: {@link RiskScoreLevelServiceImpl#deleteRiskScoreLevel(RiskScoreLevel)}
     */
    @Test
    void testDeleteRiskScoreLevel() {
        doNothing().when(riskScoreLevelRepository).deleteById(Mockito.<String>any());

        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("Level");
        riskScoreLevel.setScore("Score");
        riskScoreLevelServiceImpl.deleteRiskScoreLevel(riskScoreLevel);
        verify(riskScoreLevelRepository).deleteById(Mockito.<String>any());
        assertEquals("Level", riskScoreLevel.getLevel());
        assertEquals("Score", riskScoreLevel.getScore());
        assertTrue(riskScoreLevelServiceImpl.getAllRiskScoreLevel().isEmpty());
    }
}

