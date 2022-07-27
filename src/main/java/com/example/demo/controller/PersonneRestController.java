/*
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Personne;
import com.example.demo.services.IService;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneRestController.
 */
@RestController
//Autorise la communication entre application front-end et back-end
@CrossOrigin(origins = "*")
public class PersonneRestController {

	/** The personne service. */
	@Autowired
	private IService<Personne> personneService;

	// retourne une liste de personnes
	/**
	 * Show all.
	 *
	 * @return the response entity
	 */
	// http://localhost:8080/personnes
	@GetMapping(value = "/personnes")
	public ResponseEntity<List<Personne>> showAll() {
		return new ResponseEntity<List<Personne>>(personneService.findAll(), HttpStatus.OK);
	}

	// enregistre un objet de type personne en db
	/**
	 * Save.
	 *
	 * @param p the p
	 * @return the response entity
	 */
	// http://localhost:8080/personnes
	@PostMapping(value = "/personnes")
	public ResponseEntity<Personne> save(@RequestBody Personne p) {
		return new ResponseEntity<>(personneService.saveOrUpdate(p), HttpStatus.CREATED);
	}

	// retourne un objet de type Personne selon son identifiant
	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	// http://localhost:8080/personnes/1
	@GetMapping(value = "/personnes/{id}")
	public ResponseEntity<Personne> getOne(@PathVariable("id") Integer id) {
		Personne p = personneService.getById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + id));
//		Personne p = personneService.getById(id).get();	
//		if(p == null) {		
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + id);
//		}			
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	
	// met a jour un objet de type Personne selon son identifiant
	/**
	 * Edits the.
	 *
	 * @param personne the personne
	 * @param id the id
	 * @return the response entity
	 */
	// http://localhost:8080/personnes/1
	@PutMapping(value = "/personnes/{id}")
	public ResponseEntity<Personne> edit(@RequestBody Personne personne, @PathVariable("id") Integer id) {
		
		Personne personToUpdate = personneService.getById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + id));
		
		personToUpdate.setNom(personne.getNom());
		personToUpdate.setPrenom(personne.getPrenom());
		personToUpdate.setAge(personne.getAge());
		
		return new ResponseEntity<>(personneService.saveOrUpdate(personToUpdate), HttpStatus.OK);
	}
	
	// supprime un objet de type Personne selon son identifiant
	/**
	 * Delete personne.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	// http://localhost:8080/personnes/1
	@DeleteMapping("/personnes/{id}")
	public ResponseEntity<?> deletePersonne(@PathVariable(value = "id") Integer id) {
		Personne personToUpdate = personneService.getById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + id));
		personneService.delete(personToUpdate.getId());
		return new ResponseEntity<>("Person with " + id + " has been deleted successfully", HttpStatus.OK);
	}
}
