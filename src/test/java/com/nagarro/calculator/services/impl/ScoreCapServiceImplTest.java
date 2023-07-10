package com.nagarro.calculator.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.calculator.models.ScoreCap;
import com.nagarro.calculator.repositories.ScoreCapRepository;

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

@ContextConfiguration(classes = {ScoreCapServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScoreCapServiceImplTest {
    @MockBean
    private ScoreCapRepository scoreCapRepository;

    @Autowired
    private ScoreCapServiceImpl scoreCapServiceImpl;

    /**
     * Method under test: {@link ScoreCapServiceImpl#getAllScoreCap()}
     */
    @Test
    void testGetAllScoreCap() {
        ArrayList<ScoreCap> scoreCapList = new ArrayList<>();
        when(scoreCapRepository.findAll()).thenReturn(scoreCapList);
        List<ScoreCap> actualAllScoreCap = scoreCapServiceImpl.getAllScoreCap();
        assertSame(scoreCapList, actualAllScoreCap);
        assertTrue(actualAllScoreCap.isEmpty());
        verify(scoreCapRepository).findAll();
    }

    /**
     * Method under test: {@link ScoreCapServiceImpl#saveScoreCap(ScoreCap)}
     */
    @Test
    void testSaveScoreCap() {
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapRepository.save(Mockito.<ScoreCap>any())).thenReturn(scoreCap);

        ScoreCap scoreCap2 = new ScoreCap();
        scoreCap2.setCondition("Condition");
        scoreCap2.setTotalRiskCappedScore(3);
        assertSame(scoreCap, scoreCapServiceImpl.saveScoreCap(scoreCap2));
        verify(scoreCapRepository).save(Mockito.<ScoreCap>any());
    }

    /**
     * Method under test: {@link ScoreCapServiceImpl#getScoreCapByCondition(String)}
     */
    @Test
    void testGetScoreCapByCondition() throws IOException {
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapRepository.findByCondition(Mockito.<String>any())).thenReturn(scoreCap);
        assertSame(scoreCap, scoreCapServiceImpl.getScoreCapByCondition("Condition"));
        verify(scoreCapRepository).findByCondition(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ScoreCapServiceImpl#deleteScoreCap(ScoreCap)}
     */
    @Test
    void testDeleteScoreCap() {
        doNothing().when(scoreCapRepository).deleteById(Mockito.<String>any());

        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        scoreCapServiceImpl.deleteScoreCap(scoreCap);
        verify(scoreCapRepository).deleteById(Mockito.<String>any());
        assertEquals("Condition", scoreCap.getCondition());
        assertEquals(3, scoreCap.getTotalRiskCappedScore());
        assertTrue(scoreCapServiceImpl.getAllScoreCap().isEmpty());
    }
}

