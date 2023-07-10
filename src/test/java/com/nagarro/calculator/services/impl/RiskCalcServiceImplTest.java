package com.nagarro.calculator.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.calculator.models.RiskCalc;
import com.nagarro.calculator.repositories.RiskCalcRepository;

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

@ContextConfiguration(classes = {RiskCalcServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RiskCalcServiceImplTest {
    @MockBean
    private RiskCalcRepository riskCalcRepository;

    @Autowired
    private RiskCalcServiceImpl riskCalcServiceImpl;

    /**
     * Method under test: {@link RiskCalcServiceImpl#getAllRiskCalcLogic()}
     */
    @Test
    void testGetAllRiskCalcLogic() {
        ArrayList<RiskCalc> riskCalcList = new ArrayList<>();
        when(riskCalcRepository.findAll()).thenReturn(riskCalcList);
        List<RiskCalc> actualAllRiskCalcLogic = riskCalcServiceImpl.getAllRiskCalcLogic();
        assertSame(riskCalcList, actualAllRiskCalcLogic);
        assertTrue(actualAllRiskCalcLogic.isEmpty());
        verify(riskCalcRepository).findAll();
    }

    /**
     * Method under test: {@link RiskCalcServiceImpl#saveRiskCalc(RiskCalc)}
     */
    @Test
    void testSaveRiskCalc() {
        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");
        when(riskCalcRepository.save(Mockito.<RiskCalc>any())).thenReturn(riskCalc);

        RiskCalc riskCalc2 = new RiskCalc();
        riskCalc2.setElementName("Element Name");
        riskCalc2.setFormula("Formula");
        assertSame(riskCalc, riskCalcServiceImpl.saveRiskCalc(riskCalc2));
        verify(riskCalcRepository).save(Mockito.<RiskCalc>any());
    }

    /**
     * Method under test: {@link RiskCalcServiceImpl#getRiskCalcLogicByName(String)}
     */
    @Test
    void testGetRiskCalcLogicByName() throws IOException {
        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");
        when(riskCalcRepository.findByElementName(Mockito.<String>any())).thenReturn(riskCalc);
        assertSame(riskCalc, riskCalcServiceImpl.getRiskCalcLogicByName("Name"));
        verify(riskCalcRepository).findByElementName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link RiskCalcServiceImpl#deleteRiskCalc(RiskCalc)}
     */
    @Test
    void testDeleteRiskCalc() {
        doNothing().when(riskCalcRepository).deleteById(Mockito.<String>any());

        RiskCalc riskCalc = new RiskCalc();
        riskCalc.setElementName("Element Name");
        riskCalc.setFormula("Formula");
        riskCalcServiceImpl.deleteRiskCalc(riskCalc);
        verify(riskCalcRepository).deleteById(Mockito.<String>any());
        assertEquals("Element Name", riskCalc.getElementName());
        assertEquals("Formula", riskCalc.getFormula());
        assertTrue(riskCalcServiceImpl.getAllRiskCalcLogic().isEmpty());
    }
}

