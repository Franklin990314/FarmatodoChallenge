package com.farmatodo.challenge.service.impl;

import com.farmatodo.challenge.service.model.Character;
import com.farmatodo.challenge.service.model.Episode;
import com.farmatodo.challenge.service.model.Location;
import com.farmatodo.challenge.service.model.Origin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class WebServiceImplTest {

    @InjectMocks
    private WebServiceImpl webService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getSingleEpisode() throws Exception {
        Episode episodeResponse = this.getEpisodeInfo();

        when(restTemplate.getForObject(anyString(), any())).thenReturn(episodeResponse);

        Episode actual = webService.getSingleEpisode(18L);

        assertNotNull(actual);
    }

    @Test
    void getMultipleCharacters() throws Exception {
        List<Character> charactersResponse = this.getCharactersInfo();
        ResponseEntity<List<Character>> response = new ResponseEntity<List<Character>>(charactersResponse, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class))).thenReturn(response);

        List<Character> actual = webService.getMultipleCharacters("1,2,3");

        assertNotNull(actual);
    }

    @Test
    void getMultipleLocation() throws Exception {
        List<Location> locationsResponse = this.getLocationsInfo();
        ResponseEntity<List<Location>> response = new ResponseEntity<List<Location>>(locationsResponse, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class))).thenReturn(response);

        List<Location> actual = webService.getMultipleLocation("1,2,3");

        assertNotNull(actual);
    }

    private Episode getEpisodeInfo() {
        Episode episodeResponse = new Episode();

        episodeResponse.setId(new Long(30));
        episodeResponse.setName("The ABC's of Beth");
        episodeResponse.setCharacters(new ArrayList<>());
        episodeResponse.getCharacters().add("https://rickandmortyapi.com/api/character/1");
        episodeResponse.getCharacters().add("https://rickandmortyapi.com/api/character/2");
        episodeResponse.getCharacters().add("https://rickandmortyapi.com/api/character/3");
        episodeResponse.getCharacters().add("https://rickandmortyapi.com/api/character/4");
        episodeResponse.getCharacters().add("https://rickandmortyapi.com/api/character/5");
        episodeResponse.setAir_date("September 24, 2017");
        episodeResponse.setEpisode("S03E09");
        episodeResponse.setUrl("https://rickandmortyapi.com/api/episode/30");
        episodeResponse.setCreated("2017-11-10T12:56:36.828Z");

        return episodeResponse;
    }

    private List<Character> getCharactersInfo() {
        List<Character> charactersResponse = new ArrayList<>();

        charactersResponse.add(new Character());
        charactersResponse.get(0).setId(new Long(1));
        charactersResponse.get(0).setName("Rick Sanchez");
        charactersResponse.get(0).setStatus("Alive");
        charactersResponse.get(0).setSpecies("Human");
        charactersResponse.get(0).setType("");
        charactersResponse.get(0).setGender("Male");
        charactersResponse.get(0).setOrigin(new Origin());
        charactersResponse.get(0).getOrigin().setName("Earth (C-137)");
        charactersResponse.get(0).getOrigin().setUrl("https://rickandmortyapi.com/api/location/1");
        charactersResponse.get(0).setLocation(new Location());
        charactersResponse.get(0).getLocation().setName("Citadel of Ricks");
        charactersResponse.get(0).getLocation().setUrl("https://rickandmortyapi.com/api/location/3");
        charactersResponse.get(0).setImage("https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        charactersResponse.get(0).setEpisode(new ArrayList<>());
        charactersResponse.get(0).getEpisode().add("https://rickandmortyapi.com/api/episode/1");
        charactersResponse.get(0).getEpisode().add("https://rickandmortyapi.com/api/episode/2");
        charactersResponse.get(0).getEpisode().add("https://rickandmortyapi.com/api/episode/3");
        charactersResponse.get(0).getEpisode().add("https://rickandmortyapi.com/api/episode/4");
        charactersResponse.get(0).getEpisode().add("https://rickandmortyapi.com/api/episode/5");
        charactersResponse.get(0).setUrl("https://rickandmortyapi.com/api/character/1");
        charactersResponse.get(0).setCreated("2017-11-04T18:48:46.250Z");

        return charactersResponse;
    }

    private List<Location> getLocationsInfo() {
        List<Location> locationsResponse = new ArrayList<>();

        locationsResponse.add(new Location());
        locationsResponse.get(0).setId(new Long(3));
        locationsResponse.get(0).setName("Citadel of Ricks");
        locationsResponse.get(0).setType("Space station");
        locationsResponse.get(0).setDimension("unknown");
        locationsResponse.get(0).setResidents(new ArrayList<>());
        locationsResponse.get(0).getResidents().add("https://rickandmortyapi.com/api/character/8");
        locationsResponse.get(0).getResidents().add("https://rickandmortyapi.com/api/character/14");
        locationsResponse.get(0).getResidents().add("https://rickandmortyapi.com/api/character/15");
        locationsResponse.get(0).getResidents().add("https://rickandmortyapi.com/api/character/18");
        locationsResponse.get(0).setUrl("https://rickandmortyapi.com/api/location/3");
        locationsResponse.get(0).setCreated("2017-11-10T13:08:13.191Z");

        return locationsResponse;
    }
}