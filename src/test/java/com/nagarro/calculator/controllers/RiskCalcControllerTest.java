package com.nagarro.calculator.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.services.RiskCalcService;

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

@ContextConfiguration(classes = {RiskCalcController.class})
@ExtendWith(SpringExtension.class)
class RiskCalcControllerTest {
    @Autowired
    private RiskCalcController riskCalcController;

    @MockBean
    private RiskCalcService riskCalcService;

    /**
     * Method under test: {@link RiskCalcController#getAllRiskCalcLogic()}
     */
    @Test
    void testGetAllRiskCalcLogic() throws Exception {
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-calc-logic");
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RiskCalcController#getAllRiskCalcLogic()}
     */
    @Test
    void testGetAllRiskCalcLogic2() throws Exception {
        when(riskCalcService.getAllRiskCalcLogic()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-calc-logic");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link RiskCalcController#deleteRiskCalc(String)}
     */
    @Test
    void testDeleteRiskCalc() throws Exception {
        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");
        when(riskCalcService.getRiskCalcLogicByName(Mockito.<String>any())).thenReturn(riskCalc);
        doNothing().when(riskCalcService).deleteRiskCalc(Mockito.<RiskCalc>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-calc-logic/{elementName}",
                "Element Name");
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link RiskCalcController#deleteRiskCalc(String)}
     */
    @Test
    void testDeleteRiskCalc2() throws Exception {
        when(riskCalcService.getRiskCalcLogicByName(Mockito.<String>any())).thenThrow(new IOException("?"));
        doNothing().when(riskCalcService).deleteRiskCalc(Mockito.<RiskCalc>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/risk-calc-logic/{elementName}",
                "Element Name");
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link RiskCalcController#getRiskCalcByElementName(String)}
     */
    @Test
    void testGetRiskCalcByElementName() throws Exception {
        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");
        when(riskCalcService.getRiskCalcLogicByName(Mockito.<String>any())).thenReturn(riskCalc);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-calc-logic/{elementName}",
                "Element Name");
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"elementName\":\"Element Name\",\"formula\":\"Formula\"}"));
    }

    /**
     * Method under test: {@link RiskCalcController#getRiskCalcByElementName(String)}
     */
    @Test
    void testGetRiskCalcByElementName2() throws Exception {
        when(riskCalcService.getRiskCalcLogicByName(Mockito.<String>any())).thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/risk-calc-logic/{elementName}",
                "Element Name");
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link RiskCalcController#saveRiskCalcLogic(RiskCalc)}
     */
    @Test
    void testSaveRiskCalcLogic() throws Exception {
        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");
        when(riskCalcService.saveRiskCalc(Mockito.<RiskCalc>any())).thenReturn(riskCalc);

        RiskCalc riskCalc2 = new RiskCalc();
        riskCalc2.setElementName("Element Name");
        riskCalc2.setFormula("Formula");
        String content = (new ObjectMapper()).writeValueAsString(riskCalc2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addRiskCalcLogic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"elementName\":\"Element Name\",\"formula\":\"Formula\"}"));
    }

    /**
     * Method under test: {@link RiskCalcController#updateRiskCalc(String, RiskCalc)}
     */
    @Test
    void testUpdateRiskCalc() throws Exception {
        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");

        RiskCalc riskCalc2 = new RiskCalc();
        riskCalc2.setElementName("Element Name");
        riskCalc2.setFormula("Formula");
        when(riskCalcService.saveRiskCalc(Mockito.<RiskCalc>any())).thenReturn(riskCalc2);
        when(riskCalcService.getRiskCalcLogicByName(Mockito.<String>any())).thenReturn(riskCalc);

        RiskCalc riskCalc3 = new RiskCalc();
        riskCalc3.setElementName("Element Name");
        riskCalc3.setFormula("Formula");
        String content = (new ObjectMapper()).writeValueAsString(riskCalc3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/risk-calc-logic/{elementName}", "Element Name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(riskCalcController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"elementName\":\"Element Name\",\"formula\":\"Formula\"}"));
    }
}

