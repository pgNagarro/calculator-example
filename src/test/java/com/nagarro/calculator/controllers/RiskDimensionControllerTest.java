package com.nagarro.calculator.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculator.models.RiskDimension;
import com.nagarro.calculator.services.RiskDimensionService;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RiskDimensionController.class})
@ExtendWith(SpringExtension.class)
class RiskDimensionControllerTest {
    @Autowired
    private RiskDimensionController riskDimensionController;

    @MockBean
    private RiskDimensionService riskDimensionService;

    /**
     * Method under test: {@link RiskDimensionController#saveRiskDimension(RiskDimension)}
     */
    @Test
    void testSaveRiskDimension() throws Exception {
        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        when(riskDimensionService.saveRiskDimension(Mockito.<RiskDimension>any())).thenReturn(riskDimension);

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("Dimension");
        riskDimension2.setWeight(3);
        String content = (new ObjectMapper()).writeValueAsString(riskDimension2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addRiskDimension")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"dimension\":\"Dimension\",\"weight\":3}"));
    }

    /**
     * Method under test: {@link RiskDimensionController#saveRiskDimension(RiskDimension)}
     */
    @Test
    void testSaveRiskDimension2() throws Exception {
        when(riskDimensionService.saveRiskDimension(Mockito.<RiskDimension>any())).thenThrow(new IOException("?"));

        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        String content = (new ObjectMapper()).writeValueAsString(riskDimension);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addRiskDimension")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link RiskDimensionController#deleteRiskDimension(String)}
     */
    @Test
    void testDeleteRiskDimension() throws Exception {
        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        when(riskDimensionService.getRiskDimensionById(Mockito.<String>any())).thenReturn(riskDimension);
        doNothing().when(riskDimensionService).deleteRiskDimension(Mockito.<RiskDimension>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-dimension/{dimension}",
                "Dimension");
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link RiskDimensionController#deleteRiskDimension(String)}
     */
    @Test
    void testDeleteRiskDimension2() throws Exception {
        when(riskDimensionService.getRiskDimensionById(Mockito.<String>any())).thenThrow(new IOException("?"));
        doNothing().when(riskDimensionService).deleteRiskDimension(Mockito.<RiskDimension>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-dimension/{dimension}",
                "Dimension");
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Unable to Delete\":false}"));
    }

    /**
     * Method under test: {@link RiskDimensionController#getRiskDimension()}
     */
    @Test
    void testGetRiskDimension() throws Exception {
        when(riskDimensionService.getAllRiskDimension()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-dimension");
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RiskDimensionController#getRiskDimension()}
     */
    @Test
    void testGetRiskDimension2() throws Exception {
        when(riskDimensionService.getAllRiskDimension()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-dimension");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RiskDimensionController#getRiskDimensionByDimension(String)}
     */
    @Test
    void testGetRiskDimensionByDimension() throws Exception {
        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        when(riskDimensionService.getRiskDimensionById(Mockito.<String>any())).thenReturn(riskDimension);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-dimension/{dimension}",
                "Dimension");
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"dimension\":\"Dimension\",\"weight\":3}"));
    }

    /**
     * Method under test: {@link RiskDimensionController#getRiskDimensionByDimension(String)}
     */
    @Test
    void testGetRiskDimensionByDimension2() throws Exception {
        when(riskDimensionService.getRiskDimensionById(Mockito.<String>any())).thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-dimension/{dimension}",
                "Dimension");
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link RiskDimensionController#updateRiskDimension(String, RiskDimension)}
     */
    @Test
    void testUpdateRiskDimension() throws Exception {
        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("Dimension");
        riskDimension2.setWeight(3);
        when(riskDimensionService.saveRiskDimension(Mockito.<RiskDimension>any())).thenReturn(riskDimension2);
        when(riskDimensionService.getRiskDimensionById(Mockito.<String>any())).thenReturn(riskDimension);

        RiskDimension riskDimension3 = new RiskDimension();
        riskDimension3.setDimension("Dimension");
        riskDimension3.setWeight(3);
        String content = (new ObjectMapper()).writeValueAsString(riskDimension3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/risk-dimension/{dimension}", "Dimension")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"dimension\":\"Dimension\",\"weight\":3}"));
    }

    /**
     * Method under test: {@link RiskDimensionController#updateRiskDimension(String, RiskDimension)}
     */
    @Test
    void testUpdateRiskDimension2() throws Exception {
        RiskDimension riskDimension = new RiskDimension();
        riskDimension.setDimension("Dimension");
        riskDimension.setWeight(3);
        when(riskDimensionService.saveRiskDimension(Mockito.<RiskDimension>any())).thenThrow(new IOException("?"));
        when(riskDimensionService.getRiskDimensionById(Mockito.<String>any())).thenReturn(riskDimension);

        RiskDimension riskDimension2 = new RiskDimension();
        riskDimension2.setDimension("Dimension");
        riskDimension2.setWeight(3);
        String content = (new ObjectMapper()).writeValueAsString(riskDimension2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/risk-dimension/{dimension}", "Dimension")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(riskDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

