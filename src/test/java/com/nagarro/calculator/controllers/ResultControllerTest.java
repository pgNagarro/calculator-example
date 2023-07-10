package com.nagarro.calculator.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.nagarro.calculator.services.ResultService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ResultController.class})
@ExtendWith(SpringExtension.class)
class ResultControllerTest {
    @Autowired
    private ResultController resultController;

    @MockBean
    private ResultService resultService;

    /**
     * Method under test: {@link ResultController#displayResult()}
     */
    @Test
    void testDisplayResult() throws Exception {
        when(resultService.getResult()).thenReturn(new ArrayList<>());
        doNothing().when(resultService).calculateResult();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/result");
        MockMvcBuilders.standaloneSetup(resultController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

