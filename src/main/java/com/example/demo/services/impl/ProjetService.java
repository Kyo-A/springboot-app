package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonneRepository;
import com.example.demo.dao.ProjetRepository;
import com.example.demo.models.Projet;
import com.example.demo.services.IProjetService;
import com.example.demo.services.IService;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjetService.
 */
@Service(value = "projetService")
public class ProjetService implements IProjetService, IService<Projet> {

	/** The personne repository. */
	@Autowired
	private PersonneRepository personneRepository;

	/** The projet repository. */
	@Autowired
	private ProjetRepository projetRepository;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Projet> findAll() {
		return projetRepository.findAll();
	}

	/**
	 * Save or update.
	 *
	 * @param o the o
	 * @return the projet
	 */
	@Override
	public Projet saveOrUpdate(Projet o) {
		return projetRepository.save(o);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@Override
	public Optional<Projet> getById(Integer id) {
		return projetRepository.findById(id);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	public void delete(Integer id) {
		projetRepository.deleteById(id);
	}

	/**
	 * Gets the projets by personnes id.
	 *
	 * @param id the id
	 * @return the projets by personnes id
	 */
	@Override
	public List<Projet> getProjetsByPersonnesId(Integer id) {
		return projetRepository.findProjetByPersonnesId(id);
	}

	/**
	 * Save one projet by person.
	 *
	 * @param id            the id
	 * @param projetRequest the projet request
	 * @return the optional
	 */
	@Override
	public Optional<Projet> saveOneProjetByPerson(Integer id, Projet projetRequest) {
		Optional<Projet> projet = personneRepository.findById(id).map(personne -> {
			Projet p = new Projet();
			p.setTitre(projetRequest.getTitre());
			personne.getProjets().add(projetRequest);
			projetRepository.save(projetRequest);
			return p;
		});
		return projet;

	}

	/**
	 * Edits the one projet by person.
	 *
	 * @param personneId    the personne id
	 * @param projetId      the projet id
	 * @param projetRequest the projet request
	 * @return the optional
	 */
	@Override
	public Optional<Projet> editOneProjetByPerson(Integer personneId, Integer projetId, Projet projetRequest) {
		Optional<Projet> projet = personneRepository.findById(personneId).map(personne -> {
			Projet _projet = projetRepository.findById(projetId).get();
			_projet.setTitre(projetRequest.getTitre());
			personne.getProjets().add(_projet);
			personneRepository.save(personne);
			return _projet;
		});
		return projet;
	}

	/**
	 * Delete one projet by person.
	 *
	 * @param personneId the personne id
	 * @param projetId   the projet id
	 * @return the optional
	 */
	@Override
	public Optional<Projet> deleteOneProjetByPerson(Integer personneId, Integer projetId) {
		Optional<Projet> projet = personneRepository.findById(personneId).map(personne -> {
			Projet _projet = projetRepository.findById(projetId).get();
			personne.getProjets().remove(_projet);
			personneRepository.save(personne);
			return _projet;
		});
		return projet;
	}

	/**
	 * Assign one projet by person.
	 *
	 * @param id the id
	 * @param projetRequest the projet request
	 * @return the optional
	 */
	@Override
	public Optional<Projet> assignOneProjetByPerson(Integer id, Projet projetRequest) {
		Optional<Projet> projet = personneRepository.findById(id).map(personne -> {
			Integer projetId = projetRequest.getId();
			Projet p = projetRepository.findById(projetId).get();
			personne.getProjets().add(p);
			personneRepository.save(personne);
			return p;
		});
		return projet;
	}
}
