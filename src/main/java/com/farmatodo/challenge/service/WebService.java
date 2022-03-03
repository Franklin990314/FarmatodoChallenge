package com.farmatodo.challenge.service;

import com.farmatodo.challenge.service.model.Episode;
import com.farmatodo.challenge.service.model.Character;
import com.farmatodo.challenge.service.model.Location;

import java.util.List;

public interface WebService {
    Episode getSingleEpisode(Long id) throws Exception;
    List<Character> getMultipleCharacters(String characters) throws Exception;
    List<Location> getMultipleLocation(String locations) throws Exception;
}
