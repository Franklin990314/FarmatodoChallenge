package com.farmatodo.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="character", schema = "farmatodo")
public class CharacterModel {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false, length = 250)
    private String name;

    @Column(name="species", nullable = false, length = 250)
    private String species;

    @Column(name="gender", nullable = false, length = 250)
    private String gender;

    @Column(name="image", nullable = false, length = 250)
    private String image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "characters")
    private Set<EpisodeModel> episodes;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private LocationModel location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<EpisodeModel> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<EpisodeModel> episodes) {
        this.episodes = episodes;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}
