package com.farmatodo.challenge.dao;

import com.farmatodo.challenge.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Integer> {

}
