package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Voiture;

// TODO: Auto-generated Javadoc
/**
 * The Interface VoitureRepository.
 */
public interface VoitureRepository extends JpaRepository<Voiture, Integer> {
	
	/**
	 * Find by personne id.
	 *
	 * @param personneId the personne id
	 * @return the list
	 */
	List<Voiture> findByPersonneId(Integer personneId);
	
	/**
	 * Find by id and personne id.
	 *
	 * @param id the id
	 * @param PersonneId the personne id
	 * @return the optional
	 */
	Optional<Voiture> findByIdAndPersonneId(Integer id, Integer PersonneId);

}
