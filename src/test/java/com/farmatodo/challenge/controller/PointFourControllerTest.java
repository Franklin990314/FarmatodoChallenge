package com.farmatodo.challenge.controller;

import com.farmatodo.challenge.dto.ResponseDTO;
import com.farmatodo.challenge.logic.PointFourLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PointFourControllerTest {

    @InjectMocks
    private PointFourController pointFourController;

    @Mock
    private PointFourLogic pointFourLogic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculate() throws Exception {
        ResponseDTO response = new ResponseDTO();
        response.setResult(15L);

        when(pointFourLogic.calculate(5L)).thenReturn(response);

        ResponseEntity<Object> actual = pointFourController.calculate(5L);
        Long expected = new Long(15);

        assertEquals(expected, ((ResponseDTO) actual.getBody()).getResult());
        assertEquals(200, actual.getStatusCodeValue());
    }

    @Test
    void calculate_Fail() throws Exception {
        when(pointFourLogic.calculate(5L)).thenThrow(new NumberFormatException());

        ResponseEntity<Object> actual = pointFourController.calculate(5L);

        assertEquals(400, actual.getStatusCodeValue());
    }
}