package com.example.demo.services;

import java.util.List;
import java.util.Optional;

// TODO: Auto-generated Javadoc
/**
 * The Interface IService.
 *
 * @param <T> the generic type
 */
public interface IService<T> {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();
	
	/**
	 * Save or update.
	 *
	 * @param o the o
	 * @return the t
	 */
	T saveOrUpdate(T o);
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	Optional<T> getById(Integer id);
	
	/**
	 * Delete personne.
	 *
	 * @param id the id
	 */
	void delete(Integer id);
	

}
