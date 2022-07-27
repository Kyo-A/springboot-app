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
import com.example.demo.models.Projet;
import com.example.demo.services.IProjetService;
import com.example.demo.services.IService;
import com.example.demo.services.impl.ProjetService;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjetRestController.
 */
@RestController
@CrossOrigin(origins = "*")
public class ProjetRestController {

	/** The personne service. */
	@Autowired
	private IService<Personne> personneService;

	/** The projet service. */
	@Autowired
	private ProjetService projetService;
	
	/**
	 * Gets the all projets.
	 *
	 * @return the all projets
	 */
	@GetMapping("/projets")
	public ResponseEntity<List<Projet>> getAllProjets() {		
		List<Projet> projets = projetService.findAll();		
		return new ResponseEntity<>(projets, HttpStatus.OK);
	}
	
	/**
	 * Save.
	 *
	 * @param p the p
	 * @return the response entity
	 */
	@PostMapping(value = "/projets")
	public ResponseEntity<Projet> save(@RequestBody Projet p) {
		return new ResponseEntity<>(projetService.saveOrUpdate(p), HttpStatus.CREATED);
	}

	/**
	 * Gets the all projets by personne id.
	 *
	 * @param personneId the personne id
	 * @return the all projets by personne id
	 */
	@GetMapping("/personnes/{personneId}/projets")
	public ResponseEntity<List<Projet>> getAllProjetsByPersonneId(@PathVariable(value = "personneId") Integer personneId) {
		personneService.getById(personneId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
		List<Projet> projets = projetService.getProjetsByPersonnesId(personneId);
		return new ResponseEntity<>(projets, HttpStatus.OK);
	}
	
	/**
	 * Gets the one project by personne id.
	 *
	 * @param personneId the personne id
	 * @param projetId the projet id
	 * @return the one project by personne num
	 */
	@GetMapping("/personnes/{personneId}/projets/{projetId}")
    public ResponseEntity<Projet> getOneProjectByPersonneNum(@PathVariable(value = "personneId") Integer personneId,
                                                             @PathVariable(value = "projetId") Integer projetId){
        personneService.getById(personneId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
        Projet projet = projetService.getById(projetId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found with id : " + projetId));
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }
	
    /**
     * Adds the project by personne id.
     *
     * @param personneId the personne id
     * @param projet the projet
     * @return the response entity
     */
    @PostMapping("/personnes/{personneId}/projets")
    public ResponseEntity<Projet> addProjectByPersonneNum(@PathVariable(value = "personneId") Integer personneId, @RequestBody Projet projet){
       Projet projetToSave = projetService.saveOneProjetByPerson(personneId, projet).orElseThrow(
               () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
       
       return new ResponseEntity<>(projetToSave, HttpStatus.CREATED);
    }
    
    /**
     * Assign project by personne id.
     *
     * @param personneId the personne id
     * @param projet the projet
     * @return the response entity
     */
    @PostMapping("/personnes/{personneId}/assignProjets")
    public ResponseEntity<Projet> assignProjectByPersonneNum(@PathVariable(value = "personneId") Integer personneId, @RequestBody Projet projet){
       Projet projetToSave = projetService.assignOneProjetByPerson(personneId, projet).orElseThrow(
               () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));     
       return new ResponseEntity<>(projetToSave, HttpStatus.CREATED);
    }
    
    /**
     * Edits the project by personne id.
     *
     * @param personneId the personne id
     * @param projetId the projet id
     * @param projet the projet
     * @return the response entity
     */
    @PutMapping("/personnes/{personneId}/projets/{projetId}")
    public ResponseEntity<Projet> editProjectByPersonneNum(@PathVariable(value = "personneId") Integer personneId,
                                                           @PathVariable(value = "projetId") Integer projetId,
                                                           @RequestBody Projet projet){
        personneService.getById(personneId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
        projetService.getById(projetId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found with id : " + projetId));
        projetService.editOneProjetByPerson(personneId, projetId, projet);
        return new ResponseEntity<>(projet, HttpStatus.CREATED);
    }

    /**
     * Edits the project by personne id.
     *
     * @param personneId the personne id
     * @param projetId the projet id
     * @return the response entity
     */
    @DeleteMapping("/personnes/{personneId}/projets/{projetId}")
    public ResponseEntity<?> editProjectByPersonneNum(@PathVariable(value = "personneId") Integer personneId,
                                                      @PathVariable(value = "projetId") Integer projetId){
        personneService.getById(personneId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id : " + personneId));
        projetService.getById(projetId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found with id : " + projetId));
        projetService.deleteOneProjetByPerson(personneId, projetId);
        return new ResponseEntity<>("DELETED !!!", HttpStatus.CREATED);
    }

}
