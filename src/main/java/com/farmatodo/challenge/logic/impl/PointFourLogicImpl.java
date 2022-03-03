package com.farmatodo.challenge.logic.impl;

import com.farmatodo.challenge.dto.ResponseDTO;
import com.farmatodo.challenge.logic.PointFourLogic;
import org.springframework.stereotype.Service;

@Service
public class PointFourLogicImpl implements PointFourLogic {

    @Override
    public ResponseDTO calculate(Long number) throws Exception {
        ResponseDTO response = new ResponseDTO();

        response.setResult((long) (number * (number + 1)) / 2);

        return response;
    }
}
