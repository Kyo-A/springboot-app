package com.example.demo.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.models.Personne;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneRepositoryTest.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonneRepositoryTest {
	
	/** The personne repository. */
	@Autowired
	private PersonneRepository personneRepository;

	/**
	 * Test get personnes.
	 */
	@Test
	void testGetAll() {

		Personne personne1 = new Personne("admin", "admin", 10);
		Personne personne2 = new Personne("admin2", "admin2", 20);
		List<Personne> personneList = new ArrayList<>();
		
		personneList.add(personne1);
		personneList.add(personne2);

		personneRepository.save(personne1);
		personneRepository.save(personne2);

		List<Personne> personnesFromDb = personneRepository.findAll();
		assertEquals(personneList, personnesFromDb);	
		assertThat(personnesFromDb.equals(personneList));
	}
	
	/**
	 * Test get by id.
	 */
	@Test
	void testGetById() {
		Personne personne1 = new Personne("admin", "admin", 10);
		Personne personneSavedInDb = personneRepository.save(personne1);
		Personne personneFromDb = personneRepository.findById(personne1.getId()).get();
		assertEquals(personneSavedInDb, personneFromDb);	
		assertThat(personneFromDb.equals(personneSavedInDb));		
	}
	
	/**
	 * Test save.
	 */
	@Test
	void testSave() {		
		Personne personne1 = new Personne("admin", "admin", 10);
		Personne savedInDb = personneRepository.save(personne1);
		Personne getFromDb = personneRepository.findById(savedInDb.getId()).get();
		assertEquals(savedInDb, getFromDb);
		assertThat(getFromDb).isEqualTo(savedInDb);				
	}
	
	/**
	 * Test update.
	 */
	@Test
	void testUpdate() {
		Personne personne = new Personne("admin", "admin", 10);
		personneRepository.save(personne);
		Personne getFromDb = personneRepository.findById(personne.getId()).get();
		getFromDb.setNom("admino");
		personneRepository.save(getFromDb);
		assertThat(getFromDb.getNom()).isEqualTo("admino");
	}
	
	/**
	 * Test delete.
	 */
	@Test
	void testDelete() {
		Personne personne1 = new Personne("admin", "admin", 10);
		Personne personne2 = new Personne("admin2", "admin2", 20);
		List<Personne> personneList = new ArrayList<>();
		
		personneList.add(personne1);
		personneList.add(personne2);

		personneRepository.save(personne1);
		personneRepository.save(personne2);
		
		Personne getFromDb = personneRepository.findById(personne2.getId()).get();
		personneRepository.delete(getFromDb);
		
		assertThat(!personneList.contains(getFromDb));
	}
	

}
