package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Formation;

/**
 * The Interface FormationRepository.
 */
@Repository
public interface FormationRepository extends JpaRepository<Formation, Integer> {

}
