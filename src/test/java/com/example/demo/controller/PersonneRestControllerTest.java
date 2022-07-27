package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.models.Personne;
import com.example.demo.services.impl.PersonneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneRestControllerTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonneRestController.class)
public class PersonneRestControllerTest {
	
	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The personne service. */
	@MockBean
	private PersonneService personneService;

	/** The personne. */
	Personne personne = new Personne(1, "admin", "admin", 10);
	
	/**
	 * Test get personnes.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetPersonnes() throws Exception {

		Personne mockPersonne1 = new Personne(1, "admin", "admin", 10);
		Personne mockPersonne2 = new Personne(2, "admin", "admin", 10);
		List<Personne> personneList = new ArrayList<>();
		personneList.add(mockPersonne1);
		personneList.add(mockPersonne2);

		Mockito.when(personneService.findAll()).thenReturn(personneList);

		String URI = "/personnes";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(personneList);
		String outputInJson = result.getResponse().getContentAsString();

		assertThat(outputInJson).isEqualTo(expectedJson);

	}
	
	/**
	 * Test get personne by id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetPersonneById() throws Exception {

		Personne mockPersonne = new Personne(1, "admin", "admin", 10);

		Mockito.when(personneService.getById(mockPersonne.getId())).thenReturn(Optional.of(mockPersonne));
		
		String URI = "/personnes/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockPersonne);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	/**
	 * Test save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testSave() throws Exception {

		Personne mockPersonne = new Personne(1, "admin", "admin", 10);

		String inputInJson = this.mapToJson(mockPersonne);

		String URI = "/personnes";

		Mockito.when(personneService.saveOrUpdate(Mockito.any(Personne.class))).thenReturn(mockPersonne);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

	}
	
	/**
	 * Test update personne.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdatePersonne() throws Exception {

		Personne mockPersonne = new Personne(1, "admin", "admin", 10);

		Mockito.when(personneService.getById(mockPersonne.getId())).thenReturn(Optional.of(mockPersonne));

		Personne personneFromDB = personneService.getById(mockPersonne.getId()).get();

		personneFromDB.setNom("admino");

		String inputInJson = this.mapToJson(personneFromDB);

		String URI = "/personnes/1";

		Mockito.when(personneService.saveOrUpdate(Mockito.any(Personne.class))).thenReturn(mockPersonne);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);
	}
	
	/**
	 * Test delete personne.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeletePersonne() throws Exception {
		Personne mockPersonne = new Personne(1, "admin", "admin", 10);
		Mockito.when(personneService.getById(mockPersonne.getId())).thenReturn(Optional.of(mockPersonne));

		doNothing().when(personneService).delete(mockPersonne.getId());

		String URI = "/personnes/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andReturn();

		verify(personneService, times(1)).getById(mockPersonne.getId());
		verify(personneService, times(1)).delete(mockPersonne.getId());

	}
	
	/**
	 * Map to json.
	 *
	 * @param object the object
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}


}
