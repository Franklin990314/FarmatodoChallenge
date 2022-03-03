package com.farmatodo.challenge.controller;

import com.farmatodo.challenge.logic.PointOneLogic;
import com.farmatodo.challenge.dto.EpisodeDTO;
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

@Api(value = "PointOneController", description = "API REST for consultation of rick and morty episodes")
@RestController
@RequestMapping("/api")
public class PointOneController {

    @Autowired
    private PointOneLogic pointOneLogic;

    @ApiOperation(value = "Get a rick and morty episode", notes = "Example: /episode/28")
    @RequestMapping (value = "/episode/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getEpisodeById(@PathVariable Long id) throws Exception {
        try {
            EpisodeDTO response = pointOneLogic.getEpisode(id);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            ExceptionHandler exceptionHandler = new ExceptionHandler();
            return exceptionHandler.toResponse(exc, HttpStatus.NOT_FOUND);
        }
    }
}
