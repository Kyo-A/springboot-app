package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonneRepository;
import com.example.demo.models.Personne;
import com.example.demo.services.IService;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneService.
 */
@Service(value = "personneService")
public class PersonneService implements IService<Personne> {
	
	/** The personne repository. */
	@Autowired
	private PersonneRepository personneRepository;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Personne> findAll() {
		return personneRepository.findAll();
	}

	/**
	 * Save or update.
	 *
	 * @param o the o
	 * @return the personne
	 */
	@Override
	public Personne saveOrUpdate(Personne o) {
		return personneRepository.save(o);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	@Override
	public Optional<Personne> getById(Integer id) {
		return personneRepository.findById(id);
	}

	/**
	 * Delete personne.
	 *
	 * @param id the id
	 */
	@Override
	public void delete(Integer id) {
		personneRepository.deleteById(id);
	}

}
