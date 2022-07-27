package com.example.demo.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.PersonneRepository;
import com.example.demo.models.Personne;
import com.example.demo.services.impl.PersonneService;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneServiceTest.
 */
@RunWith(SpringRunner.class)
public class PersonneServiceTest {

	/**
	 * The Class PersonneServiceTestContextConfiguration.
	 */
	@TestConfiguration // création des beans nécessaires pour les tests
	static class PersonneServiceTestContextConfiguration {

		/**
		 * Personne service.
		 *
		 * @return the personne service
		 */
		@Bean // bean de service
		public PersonneService personneService() {
			return new PersonneService();
		}

	}

	/** The personne. */
	Personne personne = new Personne(1, "admin", "admin", 10);

	/** The personne service. */
	@Autowired
	private PersonneService personneService;

	/** The personne repository. */
	@MockBean // création d'un mockBean pour PersonneRepository
	private PersonneRepository personneRepository;

	/**
	 * Test get personnes.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetPersonnes() throws Exception {

		Personne personne = new Personne("admin", "admin", 10);

		List<Personne> allPersonnes = Arrays.asList(personne);

		Mockito.when(personneRepository.findAll()).thenReturn(allPersonnes);

		Collection<Personne> personnes = personneService.findAll();
		assertNotNull(personnes);
		assertEquals(personnes, allPersonnes);
		assertEquals(personnes.size(), allPersonnes.size());
		verify(personneRepository).findAll();
	}

	/**
	 * Test save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSave() throws Exception {

		Personne personne = new Personne("admin", "admin", 10);

		Mockito.when(personneRepository.save((personne))).thenReturn(personne);
		Personne personneSaved = personneService.saveOrUpdate(personne);
		assertNotNull(personneSaved);
		assertEquals(personne.getId(), personneSaved.getId());
		assertEquals(personne.getNom(), personneSaved.getNom());

		verify(personneRepository).save(personne);

	}

	/**
	 * Test delete.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDelete() throws Exception {
		Personne personne = new Personne(1, "admin", "admin", 10);
		Personne personneMock = new Personne(1, "admin", "admin", 10);
		Mockito.when(personneRepository.save((personne))).thenReturn(personneMock);
		Personne personneSaved = personneService.saveOrUpdate(personne);
		assertNotNull(personneSaved);
		assertEquals(personneMock.getId(), personneSaved.getId());
		personneService.delete(personneSaved.getId());

		verify(personneRepository).deleteById(1);

	}

	/**
	 * Test update personne.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdatePersonne() throws Exception {
		Personne personneToUpdate = new Personne(1, "admin", "admin", 10);
		Personne personneUpdated = new Personne(1, "admini", "admin", 10);
		Mockito.when(personneRepository.save((personneToUpdate))).thenReturn(personneUpdated);
		Personne personneFromDB = personneService.saveOrUpdate(personneToUpdate);
		assertNotNull(personneFromDB);
		assertEquals(personneUpdated.getId(), personneFromDB.getId());

	}

	/**
	 * Test get personne by id.
	 */
	@Test
	public void testGetPersonneById() {
		Personne personne = new Personne(1, "admin", "admin", 10);
		Mockito.when(personneRepository.findById(personne.getId())).thenReturn(Optional.of(personne));

		Personne personneFromDB = personneService.getById(personne.getId()).get();
		assertNotNull(personneFromDB);
		assertThat(personneFromDB.getId(), is(personne.getId()));

		verify(personneRepository).findById(personne.getId());
	}

}
