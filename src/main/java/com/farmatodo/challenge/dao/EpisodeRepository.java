package com.farmatodo.challenge.dao;

import com.farmatodo.challenge.model.EpisodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<EpisodeModel, Integer> {

}