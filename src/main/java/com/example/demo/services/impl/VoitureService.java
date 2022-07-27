package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonneRepository;
import com.example.demo.dao.VoitureRepository;
import com.example.demo.models.Voiture;
import com.example.demo.services.IVoitureService;

// TODO: Auto-generated Javadoc
/**
 * The Class VoitureService.
 */
@Service(value = "voitureService")
public class VoitureService implements IVoitureService {

	/** The personne repository. */
	@Autowired
	private PersonneRepository personneRepository;

	/** The voiture repository. */
	@Autowired
	private VoitureRepository voitureRepository;

	/**
	 * Gets the voitures by personne.
	 *
	 * @param personneId the personne id
	 * @return the voitures by personne
	 */
	// Retourne une liste de voitures selon son proprietaire(id)
	@Override
	public List<Voiture> getVoituresByPersonne(Integer personneId) {
		return voitureRepository.findByPersonneId(personneId);
	}

	/**
	 * Gets the voiture by id.
	 *
	 * @param voitureId the voiture id
	 * @return the voiture by id
	 */
	// Retourne une voiture selon son id
	@Override
	public Voiture getVoitureById(Integer voitureId) {
		return voitureRepository.findById(voitureId).get();
	}

	/**
	 * Gets the one voiture by id.
	 *
	 * @param voitureId the voiture id
	 * @param personneId the personne id
	 * @return the one voiture by id
	 */
	// Retourne une voiture(id) selon son proprietaire(id)
	@Override
	public Optional<Voiture> getOneVoitureById(Integer voitureId, Integer personneId) {
		return voitureRepository.findByIdAndPersonneId(voitureId, personneId);
	}

	// le map() est utilisé pour transformer un objet en un autre en appliquant une fonction.
	/**
	 * Save one voiture by id.
	 *
	 * @param personneId the personne id
	 * @param voiture the voiture
	 * @return the optional
	 */
	// Enregistre une voiture selon son proprietaire(id)
	@Override
	public Optional<Voiture> saveOneVoitureById(Integer personneId, Voiture voiture) {
		return personneRepository.findById(personneId).map((personne) -> {
			voiture.setPersonne(personne);
			return voitureRepository.save(voiture);
		});
	}

	/**
	 * Edits the one voiture by id.
	 *
	 * @param voitureId the voiture id
	 * @param personneId the personne id
	 * @param voiture the voiture
	 * @return the optional
	 */
	// Mettre une voiture selon son proprietaire(id)
	@Override
	public Optional<Voiture> editOneVoitureById(Integer voitureId, Integer personneId, Voiture voiture) {
		return voitureRepository.findByIdAndPersonneId(voitureId, personneId).map((v) -> {
			v.setModele(voiture.getModele());
			v.setMarque(voiture.getMarque());
			return voitureRepository.save(v);
		});
	}

	/**
	 * Delete one voiture by id.
	 *
	 * @param voitureId the voiture id
	 * @param personneId the personne id
	 * @return the optional
	 */
	// supprimer une voiture(id) selon son proprietaire(id)
	@Override
	public Optional<?> deleteOneVoitureById(Integer voitureId, Integer personneId) {
		return voitureRepository.findByIdAndPersonneId(voitureId, personneId).map((v) -> {
			voitureRepository.delete(v);
			return "Deleted";
		});
	}
}
