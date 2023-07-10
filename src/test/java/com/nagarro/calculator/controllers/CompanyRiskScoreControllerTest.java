package com.nagarro.calculator.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.calculator.services.CompanyRiskScoreService;

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

@ContextConfiguration(classes = {CompanyRiskScoreController.class})
@ExtendWith(SpringExtension.class)
class CompanyRiskScoreControllerTest {
    @Autowired
    private CompanyRiskScoreController companyRiskScoreController;

    @MockBean
    private CompanyRiskScoreService companyRiskScoreService;

    /**
     * Method under test: {@link CompanyRiskScoreController#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore() throws Exception {
        when(companyRiskScoreService.checkDataIfPresent(Mockito.<CompanyRiskScore>any())).thenReturn(true);
        doNothing().when(companyRiskScoreService).saveRiskScore(Mockito.<CompanyRiskScore>any());

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(companyRiskScore);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addRiskScore")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"companyName\":\"Company Name\",\"dimensions\":[]}"));
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#saveRiskScore(CompanyRiskScore)}
     */
    @Test
    void testSaveRiskScore2() throws Exception {
        when(companyRiskScoreService.checkDataIfPresent(Mockito.<CompanyRiskScore>any())).thenReturn(false);
        doNothing().when(companyRiskScoreService).saveRiskScore(Mockito.<CompanyRiskScore>any());

        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(companyRiskScore);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addRiskScore")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"companyName\":\"Company Name\",\"dimensions\":[]}"));
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#deleteRiskScore(String)}
     */
    @Test
    void testDeleteRiskScore() throws Exception {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        when(companyRiskScoreService.getCompanyRiskScoreByCompanyName(Mockito.<String>any())).thenReturn(companyRiskScore);
        doNothing().when(companyRiskScoreService).deleteCompanyRiskScore(Mockito.<CompanyRiskScore>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-score/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#getCompanyRiskScoreByCompanyName(String)}
     */
    @Test
    void testGetCompanyRiskScoreByCompanyName() throws Exception {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);
        when(companyRiskScoreService.getCompanyRiskScoreByCompanyName(Mockito.<String>any()))
                .thenReturn(companyRiskScore);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"companyName\":\"Company Name\",\"dimensions\":[]}"));
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#getCompanyRiskScoreByCompanyName(String)}
     */
    @Test
    void testGetCompanyRiskScoreByCompanyName2() throws Exception {
        when(companyRiskScoreService.getCompanyRiskScoreByCompanyName(Mockito.<String>any()))
                .thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#deleteRiskScore(String)}
     */
    @Test
    void testDeleteRiskScore2() throws Exception {
        when(companyRiskScoreService.getCompanyRiskScoreByCompanyName(Mockito.<String>any()))
                .thenThrow(new IOException("?"));
        doNothing().when(companyRiskScoreService).deleteCompanyRiskScore(Mockito.<CompanyRiskScore>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-score/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Unable to Delete\":false}"));
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#getCompanyRiskScore()}
     */
    @Test
    void testGetCompanyRiskScore() throws Exception {
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score");
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#getCompanyRiskScore()}
     */
    @Test
    void testGetCompanyRiskScore2() throws Exception {
        when(companyRiskScoreService.getAllCompanyRiskScore()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-score");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyRiskScoreController#updateCompanyRiskScore(String, CompanyRiskScore)}
     */
    @Test
    void testUpdateCompanyRiskScore() throws Exception {
        CompanyRiskScore companyRiskScore = new CompanyRiskScore();
        companyRiskScore.setCompanyName("Company Name");
        companyRiskScore.setDimensions(new ArrayList<>());
        companyRiskScore.setId(1);

        CompanyRiskScore companyRiskScore2 = new CompanyRiskScore();
        companyRiskScore2.setCompanyName("Company Name");
        companyRiskScore2.setDimensions(new ArrayList<>());
        companyRiskScore2.setId(1);
        when(companyRiskScoreService.updateRiskScore(Mockito.<CompanyRiskScore>any())).thenReturn(companyRiskScore2);
        when(companyRiskScoreService.getCompanyRiskScoreByCompanyName(Mockito.<String>any()))
                .thenReturn(companyRiskScore);

        CompanyRiskScore companyRiskScore3 = new CompanyRiskScore();
        companyRiskScore3.setCompanyName("Company Name");
        companyRiskScore3.setDimensions(new ArrayList<>());
        companyRiskScore3.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(companyRiskScore3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/risk-score/{companyName}", "Company Name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(companyRiskScoreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"companyName\":\"Company Name\",\"dimensions\":[]}"));
    }
}

