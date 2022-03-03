package com.farmatodo.challenge.dto;

import java.util.List;

public class EpisodeDTO {
    private Long episode;
    private String episodeName;
    private List<CharacterDTO> characters;

    public Long getEpisode() {
        return episode;
    }

    public void setEpisode(Long episode) {
        this.episode = episode;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public List<CharacterDTO> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterDTO> characters) {
        this.characters = characters;
    }
}
