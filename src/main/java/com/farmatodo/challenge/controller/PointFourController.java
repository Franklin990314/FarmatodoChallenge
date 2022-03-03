package com.farmatodo.challenge.controller;

import com.farmatodo.challenge.logic.PointFourLogic;
import com.farmatodo.challenge.dto.ResponseDTO;
import com.farmatodo.challenge.service.handler.ExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "PointFourController", description = "API REST to calculate a summation up to N")
@RestController
@RequestMapping("/api")
public class PointFourController {

    @Autowired
    private PointFourLogic pointFourLogic;

    @ApiOperation(value = "Calculate a sum up to N", notes = "Example: /summation/5")
    @RequestMapping(value = "/summation/{number}", method = RequestMethod.GET)
    public ResponseEntity<Object> calculate(@PathVariable Long number) throws Exception {
        try {
            ResponseDTO response = pointFourLogic.calculate(number);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();
            return exceptionHandler.toResponse(exc, HttpStatus.BAD_REQUEST);
        }
    }
}
