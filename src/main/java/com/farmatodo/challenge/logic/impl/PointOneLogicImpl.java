package com.farmatodo.challenge.logic.impl;

import com.farmatodo.challenge.dao.CharacterRepository;
import com.farmatodo.challenge.dao.EpisodeRepository;
import com.farmatodo.challenge.dao.LocationRepository;
import com.farmatodo.challenge.dto.CharacterDTO;
import com.farmatodo.challenge.dto.EpisodeDTO;
import com.farmatodo.challenge.dto.LocationDTO;
import com.farmatodo.challenge.logic.PointOneLogic;
import com.farmatodo.challenge.model.CharacterModel;
import com.farmatodo.challenge.model.EpisodeModel;
import com.farmatodo.challenge.model.LocationModel;
import com.farmatodo.challenge.service.WebService;
import com.farmatodo.challenge.service.model.Episode;
import com.farmatodo.challenge.service.model.Character;
import com.farmatodo.challenge.service.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

@Service
public class PointOneLogicImpl implements PointOneLogic {

    @Autowired
    private WebService webService;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public EpisodeDTO getEpisode(Long id) throws Exception {
        EpisodeDTO response = new EpisodeDTO();

        Episode episodeResponse = webService.getSingleEpisode(id);
        List<Character> charactersResponse = webService.getMultipleCharacters(this.getCharacters(episodeResponse));
        List<Location> locationsResponse = webService.getMultipleLocation(this.getLocations(charactersResponse));
        Map<Long, Location> locationMap = this.createMapLocation(locationsResponse);
        Set<CharacterModel> characters = new LinkedHashSet<>();

        response.setEpisode(id);
        response.setEpisodeName(episodeResponse.getName());
        response.setCharacters(new ArrayList<>());
        for (Character item : charactersResponse) {
            CharacterDTO character = new CharacterDTO();
            character.setName(item.getName());
            character.setSpecies(item.getSpecies());
            character.setGender(item.getGender());
            character.setImage(item.getImage());
            LocationModel locationModel = null;
            if (item.getLocation() != null && item.getLocation().getUrl() != null) {
                String url = item.getLocation().getUrl();
                String idLocation = this.getPart(url, "https://rickandmortyapi.com/api/location/", 1);
                if (idLocation != null && locationMap.containsKey(Long.parseLong(idLocation))) {
                    Location location = locationMap.get(Long.parseLong(idLocation));
                    character.setLocation(new LocationDTO());
                    character.getLocation().setName(location.getName());
                    character.getLocation().setType(location.getType());
                    character.getLocation().setDimension(location.getDimension());
                    locationModel = this.saveLocation(location);
                }
            }
            response.getCharacters().add(character);
            if (locationModel != null) characters.add(this.saveCharacter(item, locationModel));
        }
        this.saveEpisode(episodeResponse, characters);

        return response;
    }

    private LocationModel saveLocation(Location location) {
        LocationModel locationModel = null;

        try {
            locationModel = locationRepository.findById(new Integer(location.getId().intValue())).get();
        } catch (NoSuchElementException exc) { }

        if (locationModel == null) {
            locationModel = new LocationModel();
            locationModel.setId(new Integer(location.getId().intValue()));
            locationModel.setName(location.getName());
            locationModel.setType(location.getType());
            locationModel.setDimension(location.getDimension());

            locationRepository.save(locationModel);
        }

        return locationModel;
    }

    private CharacterModel saveCharacter(Character character, LocationModel locationModel) {
        CharacterModel characterModel = null;

        try {
            characterModel = characterRepository.findById(new Integer(character.getId().intValue())).get();
        } catch (NoSuchElementException exc) { }

        if (characterModel == null) {
            characterModel = new CharacterModel();
            characterModel.setId(new Integer(character.getId().intValue()));
            characterModel.setName(character.getName());
            characterModel.setSpecies(character.getSpecies());
            characterModel.setGender(character.getGender());
            characterModel.setImage(character.getImage());
        }

        characterModel.setLocation(locationModel);
        characterRepository.save(characterModel);

        return characterModel;
    }

    private void saveEpisode(Episode episode, Set<CharacterModel> characters) {
        EpisodeModel episodeModel = null;

        try {
            episodeModel = episodeRepository.findById(new Integer(episode.getId().intValue())).get();
        } catch (NoSuchElementException exc) { }

        if (episodeModel == null) {
            episodeModel = new EpisodeModel();
            episodeModel.setId(new Integer(episode.getId().intValue()));
            episodeModel.setName(episode.getName());
            episodeModel.setCharacters(characters);

            episodeRepository.save(episodeModel);
        }
    }

    private Map<Long, Location> createMapLocation(List<Location> locationsResponse) {
        Map<Long, Location> response = new TreeMap<>();

        for (Location location : locationsResponse) {
            response.put(new Long(location.getId()), location);
        }

        return response;
    }

    private String getCharacters(Episode episode) {
        Set<Long> characters = new LinkedHashSet<>();

        if (episode.getCharacters() != null && !episode.getCharacters().isEmpty()) {
            for (String str : episode.getCharacters()) {
                if (str.contains("https://rickandmortyapi.com/api/character/")) {
                    String id = this.getPart(str, "https://rickandmortyapi.com/api/character/", 1);
                    if (id != null) characters.add(Long.parseLong(id));
                }
            }
        }

        return characters.toString();
    }

    private String getLocations(List<Character> characters) {
        Set<Long> location = new LinkedHashSet<>();

        for (Character item : characters) {
            if (item.getLocation() != null && item.getLocation().getUrl() != null) {
                String url = item.getLocation().getUrl();
                if (url.contains("https://rickandmortyapi.com/api/location/")) {
                    String id = this.getPart(url, "https://rickandmortyapi.com/api/location/", 1);
                    if (id != null) location.add(Long.parseLong(id));
                }
            }
        }

        return location.toString();
    }

    private String getPart(String str, String regex, int index) {
        String[] parts = str.split(regex);
        if (parts.length > index) {
            return parts[1];
        }
        return null;
    }
}
