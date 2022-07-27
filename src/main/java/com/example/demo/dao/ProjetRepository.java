package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Projet;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProjetRepository.
 */
@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer>{
	
	/**
	 * Find projet by personnes id.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<Projet> findProjetByPersonnesId(Integer id);

}
