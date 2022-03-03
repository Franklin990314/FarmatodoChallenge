package com.farmatodo.challenge.logic.impl;

import com.farmatodo.challenge.dto.NumbersDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PointThreeLogicImplTest {

    @InjectMocks
    private PointThreeLogicImpl pointThreeLogic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateHappyNumbers() throws Exception {
        String strNumbers = "1,7,15,33,7,33";
        NumbersDTO actual = pointThreeLogic.calculateHappyNumbers(strNumbers);

        // number: 1
        Boolean expected = new Boolean(true);
        assertEquals(expected, actual.getNumbers().get(0).getIsHappy());

        // number: 7
        expected = new Boolean(true);
        assertEquals(expected, actual.getNumbers().get(1).getIsHappy());

        // number: 15
        expected = new Boolean(false);
        assertEquals(expected, actual.getNumbers().get(2).getIsHappy());

        // number: 33
        expected = new Boolean(false);
        assertEquals(expected, actual.getNumbers().get(3).getIsHappy());

        // number: 7
        expected = new Boolean(true);
        assertEquals(expected, actual.getNumbers().get(4).getIsHappy());

        // number: 33
        expected = new Boolean(false);
        assertEquals(expected, actual.getNumbers().get(5).getIsHappy());
    }

    @Test
    void calculateHappyNumbers_Fail() throws Exception {
        String strNumbers = "1,7,15.3";

        Exception actualException = null;
        try {
            pointThreeLogic.calculateHappyNumbers(strNumbers);
        } catch (Exception exc) {
            actualException = exc;
        }

        String expected = "'15.3' no es posible calcular si es feliz o no";

        assertEquals(expected, actualException.getMessage());
    }
}