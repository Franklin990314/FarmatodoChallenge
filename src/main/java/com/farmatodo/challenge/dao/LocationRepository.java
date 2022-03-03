package com.farmatodo.challenge.dao;

import com.farmatodo.challenge.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel, Integer> {

}