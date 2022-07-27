package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Personne;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonneRepository.
 */
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {
	
	/**
	 * Find by nom and prenom.
	 *
	 * @param nom the nom
	 * @param prenom the prenom
	 * @return the list
	 */
	List<Personne> findByNomAndPrenom(String nom, String prenom);
	
	/**
	 * Find by nom.
	 *
	 * @param nom the nom
	 * @return the list
	 */
	@Query("select p from Personne p where p.nom = ?1")
	List<Personne> findByNom(String nom);
	
}
