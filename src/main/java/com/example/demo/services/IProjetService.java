package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Projet;

// TODO: Auto-generated Javadoc
/**
 * The Interface IProjetService.
 */
public interface IProjetService {

	/**
	 * Gets the projets by personnes id.
	 *
	 * @param id the id
	 * @return the projets by personnes id
	 */
	List<Projet> getProjetsByPersonnesId(Integer id);

	/**
	 * Save one projet by person.
	 *
	 * @param id the id
	 * @param projetRequest the projet request
	 * @return the optional
	 */
	Optional<Projet> saveOneProjetByPerson(Integer id, Projet projetRequest);
	
	/**
	 * Assign one projet by person.
	 *
	 * @param id the id
	 * @param projetRequest the projet request
	 * @return the optional
	 */
	Optional<Projet> assignOneProjetByPerson(Integer id, Projet projetRequest);

	/**
	 * Edits the one projet by person.
	 *
	 * @param personneId the personne id
	 * @param projetId the projet id
	 * @param projetRequest the projet request
	 * @return the optional
	 */
	Optional<Projet> editOneProjetByPerson(Integer personneId, Integer projetId, Projet projetRequest);

	/**
	 * Delete one projet by person.
	 *
	 * @param personneId the personne id
	 * @param projetId the projet id
	 * @return the optional
	 */
	Optional<Projet> deleteOneProjetByPerson(Integer personneId, Integer projetId);
}
