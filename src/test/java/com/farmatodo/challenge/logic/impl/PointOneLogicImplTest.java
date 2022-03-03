package com.farmatodo.challenge.logic.impl;

import com.farmatodo.challenge.dao.CharacterRepository;
import com.farmatodo.challenge.dao.EpisodeRepository;
import com.farmatodo.challenge.dao.LocationRepository;
import com.farmatodo.challenge.dto.EpisodeDTO;
import com.farmatodo.challenge.service.WebService;
import com.farmatodo.challenge.service.model.Character;
import com.farmatodo.challenge.service.model.Episode;
import com.farmatodo.challenge.service.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PointOneLogicImplTest {

    @InjectMocks
    private PointOneLogicImpl pointOneLogic;

    @Mock
    private WebService webService;

    @Mock
    private EpisodeRepository episodeRepository;

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getEpisode() throws Exception {
        Episode episodeResponse = this.getEpisodeInfo();
        List<Character> charactersResponse = this.getCharactersInfo();
        List<Location> locationsResponse = this.getLocationsInfo();

        when(webService.getSingleEpisode(anyLong())).thenReturn(episodeResponse);
        when(webService.getMultipleCharacters(anyString())).thenReturn(charactersResponse);
        when(webService.getMultipleLocation(anyString())).thenReturn(locationsResponse);
        when(episodeRepository.findById(anyInt())).thenThrow(new NoSuchElementException());
        when(characterRepository.findById(anyInt())).thenThrow(new NoSuchElementException());
        when(locationRepository.findById(anyInt())).thenThrow(new NoSuchElementException());

        EpisodeDTO actual = pointOneLogic.getEpisode(15L);

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

        return episodeResponse;
    }

    private List<Character> getCharactersInfo() {
        List<Character> charactersResponse = new ArrayList<>();

        charactersResponse.add(new Character());
        charactersResponse.get(0).setId(new Long(1));
        charactersResponse.get(0).setName("Rick Sanchez");
        charactersResponse.get(0).setSpecies("Human");
        charactersResponse.get(0).setGender("Male");
        charactersResponse.get(0).setImage("https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        charactersResponse.get(0).setLocation(new Location());
        charactersResponse.get(0).getLocation().setName("Citadel of Ricks");
        charactersResponse.get(0).getLocation().setUrl("https://rickandmortyapi.com/api/location/3");

        return charactersResponse;
    }

    private List<Location> getLocationsInfo() {
        List<Location> locationsResponse = new ArrayList<>();

        locationsResponse.add(new Location());
        locationsResponse.get(0).setId(new Long(3));
        locationsResponse.get(0).setName("Citadel of Ricks");
        locationsResponse.get(0).setType("Space station");
        locationsResponse.get(0).setDimension("unknown");

        return locationsResponse;
    }
}