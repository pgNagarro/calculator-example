package com.nagarro.calculator.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculator.models.ScoreCap;
import com.nagarro.calculator.services.ScoreCapService;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ScoreCapController.class})
@ExtendWith(SpringExtension.class)
class ScoreCapControllerTest {
    @Autowired
    private ScoreCapController scoreCapController;

    @MockBean
    private ScoreCapService scoreCapService;

    /**
     * Method under test: {@link ScoreCapController#saveScoreCap(ScoreCap)}
     */
    @Test
    void testSaveScoreCap() throws Exception {
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapService.saveScoreCap(Mockito.<ScoreCap>any())).thenReturn(scoreCap);

        ScoreCap scoreCap2 = new ScoreCap();
        scoreCap2.setCondition("Condition");
        scoreCap2.setTotalRiskCappedScore(3);
        String content = (new ObjectMapper()).writeValueAsString(scoreCap2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addScoreCap")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"condition\":\"Condition\",\"totalRiskCappedScore\":3}"));
    }

    /**
     * Method under test: {@link ScoreCapController#deleteScoreCap(String)}
     */
    @Test
    void testDeleteScoreCap() throws Exception {
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenReturn(scoreCap);
        doNothing().when(scoreCapService).deleteScoreCap(Mockito.<ScoreCap>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/score-cap/{condition}", "Condition");
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link ScoreCapController#deleteScoreCap(String)}
     */
    @Test
    void testDeleteScoreCap2() throws Exception {
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenThrow(new IOException("?"));
        doNothing().when(scoreCapService).deleteScoreCap(Mockito.<ScoreCap>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/score-cap/{condition}",
                "Condition");
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Unable to Delete\":false}"));
    }

    /**
     * Method under test: {@link ScoreCapController#getScoreCap()}
     */
    @Test
    void testGetScoreCap() throws Exception {
        when(scoreCapService.getAllScoreCap()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-cap");
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ScoreCapController#getScoreCap()}
     */
    @Test
    void testGetScoreCap2() throws Exception {
        when(scoreCapService.getAllScoreCap()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-cap");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ScoreCapController#getSoreCap(String)}
     */
    @Test
    void testGetSoreCap() throws Exception {
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenReturn(scoreCap);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-cap/{condition}", "Condition");
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"condition\":\"Condition\",\"totalRiskCappedScore\":3}"));
    }

    /**
     * Method under test: {@link ScoreCapController#getSoreCap(String)}
     */
    @Test
    void testGetSoreCap2() throws Exception {
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-cap/{condition}", "Condition");
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ScoreCapController#updateScoreCap(String, ScoreCap)}
     */
    @Test
    void testUpdateScoreCap() throws Exception {
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);

        ScoreCap scoreCap2 = new ScoreCap();
        scoreCap2.setCondition("Condition");
        scoreCap2.setTotalRiskCappedScore(3);
        when(scoreCapService.saveScoreCap(Mockito.<ScoreCap>any())).thenReturn(scoreCap2);
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenReturn(scoreCap);

        ScoreCap scoreCap3 = new ScoreCap();
        scoreCap3.setCondition("Condition");
        scoreCap3.setTotalRiskCappedScore(3);
        String content = (new ObjectMapper()).writeValueAsString(scoreCap3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/score-cap/{condition}", "Condition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"condition\":\"Condition\",\"totalRiskCappedScore\":3}"));
    }
}

