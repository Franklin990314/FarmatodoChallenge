package com.farmatodo.challenge.controller;

import com.farmatodo.challenge.dto.NumberDTO;
import com.farmatodo.challenge.dto.NumbersDTO;
import com.farmatodo.challenge.logic.PointFourLogic;
import com.farmatodo.challenge.logic.PointThreeLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PointThreeControllerTest {

    @InjectMocks
    private PointThreeController pointThreeController;

    @Mock
    private PointThreeLogic pointThreeLogic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateHappyNumbers() throws Exception {
        NumbersDTO response = this.getNumbers();

        when(pointThreeLogic.calculateHappyNumbers("7,11")).thenReturn(response);

        ResponseEntity<Object> actual = pointThreeController.calculateHappyNumbers("7,11");

        assertNotNull(((NumbersDTO) actual.getBody()));
        assertEquals(200, actual.getStatusCodeValue());
    }

    @Test
    void calculateHappyNumbers_Fail() throws Exception {
        when(pointThreeLogic.calculateHappyNumbers("7,11.3")).thenThrow(new Exception("'11.3' no es posible calcular si es feliz o no"));

        ResponseEntity<Object> actual = pointThreeController.calculateHappyNumbers("7,11.3");

        assertEquals(400, actual.getStatusCodeValue());
    }

    private NumbersDTO getNumbers() {
        NumbersDTO response = new NumbersDTO();

        response.setNumbers(new ArrayList<>());
        response.getNumbers().add(new NumberDTO());
        response.getNumbers().get(0).setNumber(7L);
        response.getNumbers().get(0).setIsHappy(true);
        response.getNumbers().add(new NumberDTO());
        response.getNumbers().get(1).setNumber(11L);
        response.getNumbers().get(1).setIsHappy(false);

        return response;
    }
}