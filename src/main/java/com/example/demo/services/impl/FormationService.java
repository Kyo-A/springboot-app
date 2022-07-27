package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FormationRepository;
import com.example.demo.models.Formation;
import com.example.demo.models.Personne;
import com.example.demo.services.IService;

// TODO: Auto-generated Javadoc
/**
 * The Class FormationService.
 */
@Service(value = "formationService")
public class FormationService implements IService<Formation> {
	
	/** The formation repository. */
	@Autowired
	private FormationRepository formationRepository;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Formation> findAll() {
		return formationRepository.findAll();
	}

	/**
	 * Save or update.
	 *
	 * @param o the o
	 * @return the formation
	 */
	@Override
	public Formation saveOrUpdate(Formation o) {
		return formationRepository.save(o);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@Override
	public Optional<Formation> getById(Integer id) {
		return formationRepository.findById(id);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	public void delete(Integer id) {
		formationRepository.deleteById(id);		
	}

}
