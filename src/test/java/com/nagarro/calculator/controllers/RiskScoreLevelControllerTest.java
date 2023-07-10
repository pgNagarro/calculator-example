package com.nagarro.calculator.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculator.models.RiskScoreLevel;
import com.nagarro.calculator.services.RiskScoreLevelService;

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

@ContextConfiguration(classes = {RiskScoreLevelController.class})
@ExtendWith(SpringExtension.class)
class RiskScoreLevelControllerTest {
    @Autowired
    private RiskScoreLevelController riskScoreLevelController;

    @MockBean
    private RiskScoreLevelService riskScoreLevelService;

    /**
     * Method under test: {@link RiskScoreLevelController#saveRiskScoreLevel(RiskScoreLevel)}
     */
    @Test
    void testSaveRiskScoreLevel() throws Exception {
        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("Level");
        riskScoreLevel.setScore("Score");
        when(riskScoreLevelService.saveRiskScoreLevel(Mockito.<RiskScoreLevel>any())).thenReturn(riskScoreLevel);

        RiskScoreLevel riskScoreLevel2 = new RiskScoreLevel();
        riskScoreLevel2.setLevel("Level");
        riskScoreLevel2.setScore("Score");
        String content = (new ObjectMapper()).writeValueAsString(riskScoreLevel2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addRiskScoreLevel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"score\":\"Score\",\"level\":\"Level\"}"));
    }

    /**
     * Method under test: {@link RiskScoreLevelController#getRiskScoreLevelByScore(String)}
     */
    @Test
    void testGetRiskScoreLevelByScore() throws Exception {
        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("Level");
        riskScoreLevel.setScore("Score");
        when(riskScoreLevelService.getRiskScoreLevelByScore(Mockito.<String>any())).thenReturn(riskScoreLevel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score-level/{score}", "Score");
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"score\":\"Score\",\"level\":\"Level\"}"));
    }

    /**
     * Method under test: {@link RiskScoreLevelController#getRiskScoreLevelByScore(String)}
     */
    @Test
    void testGetRiskScoreLevelByScore2() throws Exception {
        when(riskScoreLevelService.getRiskScoreLevelByScore(Mockito.<String>any())).thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score-level/{score}", "Score");
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link RiskScoreLevelController#deleteRiskScoreLevel(String)}
     */
    @Test
    void testDeleteRiskScoreLevel() throws Exception {
        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("Level");
        riskScoreLevel.setScore("Score");
        when(riskScoreLevelService.getRiskScoreLevelByScore(Mockito.<String>any())).thenReturn(riskScoreLevel);
        doNothing().when(riskScoreLevelService).deleteRiskScoreLevel(Mockito.<RiskScoreLevel>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-score-level/{score}", "Score");
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link RiskScoreLevelController#deleteRiskScoreLevel(String)}
     */
    @Test
    void testDeleteRiskScoreLevel2() throws Exception {
        when(riskScoreLevelService.getRiskScoreLevelByScore(Mockito.<String>any())).thenThrow(new IOException("?"));
        doNothing().when(riskScoreLevelService).deleteRiskScoreLevel(Mockito.<RiskScoreLevel>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-score-level/{score}",
                "Score");
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link RiskScoreLevelController#getRiskScoreLevel()}
     */
    @Test
    void testGetRiskScoreLevel() throws Exception {
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score-level");
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RiskScoreLevelController#getRiskScoreLevel()}
     */
    @Test
    void testGetRiskScoreLevel2() throws Exception {
        when(riskScoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score-level");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RiskScoreLevelController#updateRiskScoreLevel(String, RiskScoreLevel)}
     */
    @Test
    void testUpdateRiskScoreLevel() throws Exception {
        RiskScoreLevel riskScoreLevel = new RiskScoreLevel();
        riskScoreLevel.setLevel("Level");
        riskScoreLevel.setScore("Score");

        RiskScoreLevel riskScoreLevel2 = new RiskScoreLevel();
        riskScoreLevel2.setLevel("Level");
        riskScoreLevel2.setScore("Score");
        when(riskScoreLevelService.saveRiskScoreLevel(Mockito.<RiskScoreLevel>any())).thenReturn(riskScoreLevel2);
        when(riskScoreLevelService.getRiskScoreLevelByScore(Mockito.<String>any())).thenReturn(riskScoreLevel);

        RiskScoreLevel riskScoreLevel3 = new RiskScoreLevel();
        riskScoreLevel3.setLevel("Level");
        riskScoreLevel3.setScore("Score");
        String content = (new ObjectMapper()).writeValueAsString(riskScoreLevel3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/risk-score-level/{score}", "Score")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(riskScoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"score\":\"Score\",\"level\":\"Level\"}"));
    }
}

