package com.farmatodo.challenge.controller;

import com.farmatodo.challenge.dto.CharacterDTO;
import com.farmatodo.challenge.dto.EpisodeDTO;
import com.farmatodo.challenge.dto.LocationDTO;
import com.farmatodo.challenge.logic.PointOneLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PointOneControllerTest {

    @InjectMocks
    private PointOneController pointOneController;

    @Mock
    private PointOneLogic pointOneLogic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getEpisodeById() throws Exception {
        EpisodeDTO response = this.getEpisode();

        when(pointOneLogic.getEpisode(15L)).thenReturn(response);

        ResponseEntity<Object> actual = pointOneController.getEpisodeById(15L);

        assertNotNull(((EpisodeDTO) actual.getBody()));
        assertEquals(200, actual.getStatusCodeValue());

    }

    @Test
    void getEpisodeById_Fail() throws Exception {
        when(pointOneLogic.getEpisode(15L)).thenThrow(new NoSuchElementException());

        ResponseEntity<Object> actual = pointOneController.getEpisodeById(15L);

        assertEquals(404, actual.getStatusCodeValue());
    }

    private EpisodeDTO getEpisode() {
        EpisodeDTO response = new EpisodeDTO();

        response.setEpisode(15L);
        response.setEpisodeName("Total Rickall");
        response.setCharacters(new ArrayList<>());
        response.getCharacters().add(new CharacterDTO());
        response.getCharacters().get(0).setName("Rick Sanchez");
        response.getCharacters().get(0).setSpecies("Human");
        response.getCharacters().get(0).setGender("Male");
        response.getCharacters().get(0).setImage("https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        response.getCharacters().get(0).setLocation(new LocationDTO());
        response.getCharacters().get(0).getLocation().setName("Citadel of Ricks");
        response.getCharacters().get(0).getLocation().setType("Space station");
        response.getCharacters().get(0).getLocation().setDimension("unknown");
        response.getCharacters().add(new CharacterDTO());
        response.getCharacters().get(1).setName("Morty Smith");
        response.getCharacters().get(1).setSpecies("Human");
        response.getCharacters().get(1).setGender("Male");
        response.getCharacters().get(1).setImage("https://rickandmortyapi.com/api/character/avatar/2.jpeg");
        response.getCharacters().get(1).setLocation(new LocationDTO());
        response.getCharacters().get(1).getLocation().setName("Citadel of Ricks");
        response.getCharacters().get(1).getLocation().setType("Space station");
        response.getCharacters().get(1).getLocation().setDimension("unknown");

        return response;
    }
}