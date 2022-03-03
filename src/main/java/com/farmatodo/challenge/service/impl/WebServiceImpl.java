package com.farmatodo.challenge.service.impl;

import com.farmatodo.challenge.service.WebService;
import com.farmatodo.challenge.service.model.Episode;
import com.farmatodo.challenge.service.model.Character;
import com.farmatodo.challenge.service.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WebServiceImpl implements WebService {

    private static final String URL_HOST = "https://rickandmortyapi.com";
    private static final String URL_SERVICE_EPISODE = "/api/episode/";
    private static final String URL_SERVICE_CHARACTER = "/api/character/";
    private static final String URL_SERVICE_LOCATION = "/api/location/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Episode getSingleEpisode(Long id) throws Exception {
        String url = URL_HOST + URL_SERVICE_EPISODE + id;

        Episode response = restTemplate.getForObject(url, Episode.class);

        return response;
    }

    @Override
    public List<Character> getMultipleCharacters(String characters) throws Exception {
        String url = URL_HOST + URL_SERVICE_CHARACTER + characters;

        ResponseEntity<List<Character>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Character>>() {});
        List<Character> response = responseEntity.getBody();

        return response;
    }

    @Override
    public List<Location> getMultipleLocation(String locations) throws Exception {
        String url = URL_HOST + URL_SERVICE_LOCATION + locations;

        ResponseEntity<List<Location>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {});
        List<Location> response = responseEntity.getBody();

        return response;
    }
}
