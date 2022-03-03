package com.farmatodo.challenge.logic.impl;

import com.farmatodo.challenge.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointFourLogicImplTest {

    @InjectMocks
    private PointFourLogicImpl pointFourLogic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculate() throws Exception {
        // N = 5 resultado esperado = 15
        Long n = new Long(5);
        ResponseDTO actual = pointFourLogic.calculate(n);
        Long expected = new Long(15);

        assertEquals(expected, actual.getResult());

        // N = 10 resultado esperado = 55
        n = new Long(10);
        actual = pointFourLogic.calculate(n);
        expected = new Long(55);

        assertEquals(expected, actual.getResult());
    }
}