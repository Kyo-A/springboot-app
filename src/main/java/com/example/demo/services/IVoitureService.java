	package com.example.demo.services;
	
	import java.util.List;
	import java.util.Optional;
	
	import com.example.demo.models.Voiture;
	
	// TODO: Auto-generated Javadoc
/**
	 * The Interface IVoitureService.
	 */
	public interface IVoitureService {
	
		/**
		 * Gets the voitures by personne.
		 *
		 * @param personneId the personne id
		 * @return the voitures by personne
		 */
		List<Voiture> getVoituresByPersonne(Integer personneId);
	
		/**
		 * Gets the voiture by id.
		 *
		 * @param voitureId the voiture id
		 * @return the voiture by id
		 */
		Voiture getVoitureById(Integer voitureId);
	
		/**
		 * Gets the one voiture by id.
		 *
		 * @param voitureId the voiture id
		 * @param personneId the personne id
		 * @return the one voiture by id
		 */
		Optional<Voiture> getOneVoitureById(Integer voitureId, Integer personneId);
	
		/**
		 * Save one voiture by id.
		 *
		 * @param personneId the personne id
		 * @param voiture the voiture
		 * @return the optional
		 */
		Optional<Voiture> saveOneVoitureById(Integer personneId, Voiture voiture);
	
		/**
		 * Edits the one voiture by id.
		 *
		 * @param voitureId the voiture id
		 * @param personneId the personne id
		 * @param voiture the voiture
		 * @return the optional
		 */
		Optional<Voiture> editOneVoitureById(Integer voitureId, Integer personneId, Voiture voiture);
	
		/**
		 * Delete one voiture by id.
		 *
		 * @param voitureId the voiture id
		 * @param personneId the personne id
		 * @return the optional
		 */
		Optional<?> deleteOneVoitureById(Integer voitureId, Integer personneId);
	
	}
