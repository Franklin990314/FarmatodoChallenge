package com.farmatodo.challenge.controller;

import com.farmatodo.challenge.logic.PointThreeLogic;
import com.farmatodo.challenge.dto.NumbersDTO;
import com.farmatodo.challenge.service.handler.ExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "PointThreeController", description = "API REST to know which numbers are happy")
@RestController
@RequestMapping("/api")
public class PointThreeController {

    @Autowired
    private PointThreeLogic pointThreeLogic;

    @ApiOperation(value = "Get the happy numbers", notes = "Example: /happynumber/10,33,331,123")
    @RequestMapping (value = "/happynumber/{numbers}", method = RequestMethod.GET)
    public ResponseEntity<Object> calculateHappyNumbers(@PathVariable String numbers) throws Exception {
        try {
            NumbersDTO response = pointThreeLogic.calculateHappyNumbers(numbers);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();
            return exceptionHandler.toResponse(exc, HttpStatus.BAD_REQUEST);
        }
    }
}
