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
import com.example.demo.models.Voiture;
import com.example.demo.services.IService;
import com.example.demo.services.IVoitureService;

// TODO: Auto-generated Javadoc
/**
 * The Class VoitureRestController.
 */
@RestController
@CrossOrigin(origins = "*")
public class VoitureRestController {

	/** The personne service. */
	@Autowired
	private IService<Personne> personneService;

	/** The voiture service. */
	@Autowired
	private IVoitureService voitureService;

	/**
	 * Gets the all voitures by personne.
	 *
	 * @param personneId the personne id
	 * @return the all voitures by personne
	 */
	@GetMapping(value = "/personnes/{personneId}/voitures")
	public ResponseEntity<List<Voiture>> getAllVoituresByPersonne(@PathVariable("personneId") Integer personneId) {
		personneService.getById(personneId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
		List<Voiture> voitures = voitureService.getVoituresByPersonne(personneId);
		return new ResponseEntity<>(voitures, HttpStatus.OK);
	}

	/**
	 * Gets the one voiture by personne.
	 *
	 * @param personneId the personne id
	 * @param voitureId the voiture id
	 * @return the one voiture by personne
	 */
	@GetMapping(value = "/personnes/{personneId}/voitures/{voitureId}")
	public ResponseEntity<Voiture> getOneVoitureByPersonne(@PathVariable("personneId") Integer personneId,
			@PathVariable("voitureId") Integer voitureId) {
		personneService.getById(personneId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));

		Voiture v = voitureService.getOneVoitureById(voitureId, personneId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voiture not found with id : " + voitureId));

		return new ResponseEntity<>(v, HttpStatus.OK);
	}

	/**
	 * Save one voiture by personne.
	 *
	 * @param voiture the voiture
	 * @param personneId the personne id
	 * @return the response entity
	 */
	@PostMapping(value = "/personnes/{personneId}/voitures")
	public ResponseEntity<Voiture> saveOneVoitureByPersonne(@RequestBody Voiture voiture,
			@PathVariable("personneId") Integer personneId) {
		voitureService.saveOneVoitureById(personneId, voiture).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
		return new ResponseEntity<>(voiture, HttpStatus.OK);
	}

	/**
	 * Edits the car by person.
	 *
	 * @param personneId the personne id
	 * @param voitureId the voiture id
	 * @param voiture the voiture
	 * @return the response entity
	 */
	@PutMapping(value = "/personnes/{personneId}/voitures/{voitureId}")
    public ResponseEntity<Voiture> editCarByPerson(@PathVariable("personneId") Integer personneId, @PathVariable("voitureId") Integer voitureId, @RequestBody Voiture voiture){

        personneService.getById(personneId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));

        Voiture v = voitureService.getOneVoitureById(voitureId, personneId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voiture not found with id : " + voitureId));

        v.setModele(voiture.getModele());
        v.setMarque(voiture.getMarque());

        voitureService.editOneVoitureById(voitureId, personneId, v);

        return new ResponseEntity<>(v, HttpStatus.OK);
    }

    /**
     * Delete car by person.
     *
     * @param personneId the personne id
     * @param voitureId the voiture id
     * @return the response entity
     */
    @DeleteMapping(value = "/personnes/{personneId}/voitures/{voitureId}")
    public ResponseEntity<?> deleteCarByPerson(@PathVariable("personneId") Integer personneId, @PathVariable("voitureId") Integer voitureId){
        personneService.getById(personneId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
        voitureService.deleteOneVoitureById(voitureId, personneId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found with id : " + voitureId));
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
