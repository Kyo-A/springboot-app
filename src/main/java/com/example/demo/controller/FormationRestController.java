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

import com.example.demo.models.Formation;
import com.example.demo.services.IService;

// TODO: Auto-generated Javadoc
/**
 * The Class FormationRestController.
 */
@RestController
@CrossOrigin(origins = "*")
public class FormationRestController {
	
	/** The formation service. */
	@Autowired
	private IService<Formation> formationService;
	
	/**
	 * Show all.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/formations")
	public ResponseEntity<List<Formation>> showAll() {
		return new ResponseEntity<>(formationService.findAll(), HttpStatus.OK);
	}

	/**
	 * Save.
	 *
	 * @param f the f
	 * @return the response entity
	 */
	@PostMapping(value = "/formations")
	public ResponseEntity<Formation> save(@RequestBody Formation f) {
		return new ResponseEntity<>(formationService.saveOrUpdate(f), HttpStatus.CREATED);
	}
	
	/**
	 * Gets the one.
	 *
	 * @param id the id
	 * @return the one
	 */
	@GetMapping(value = "/formations/{id}")
	public ResponseEntity<Formation> getOne(@PathVariable("id") Integer id) {
		Formation f = formationService.getById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formation not found with id : " + id));		
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
	
	/**
	 * Edits the.
	 *
	 * @param formation the formation
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping(value = "/formations/{id}")
	public ResponseEntity<Formation> edit(@RequestBody Formation formation, @PathVariable("id") Integer id) {
		
		Formation formationToUpdate = formationService.getById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formation not found with id : " + id));
		
		formationToUpdate.setLibelle(formation.getLibelle());
		formationToUpdate.setDescription(formation.getDescription());
		formationToUpdate.setDateDebut(formation.getDateDebut());
		formationToUpdate.setDateFin(formation.getDateFin());
		
		return new ResponseEntity<>(formationService.saveOrUpdate(formationToUpdate), HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/formations/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		Formation formationToUpdate = formationService.getById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formation not found with id : " + id));
		formationService.delete(formationToUpdate.getId());
		return new ResponseEntity<>("Formation with " + id + " has been deleted successfully", HttpStatus.OK);
	}
}
