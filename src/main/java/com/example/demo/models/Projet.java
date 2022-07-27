package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Projet.
 */
@Entity
public class Projet {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** The titre. */
	private String titre;

	/** The personnes. */
	@ManyToMany(mappedBy = "projets", cascade = { CascadeType.MERGE })
	@JsonIgnore
	public List<Personne> personnes = new ArrayList<>();

	/**
	 * Instantiates a new projet.
	 */
	public Projet() {
		super();
	}

	/**
	 * Instantiates a new projet.
	 *
	 * @param id the id
	 * @param titre the titre
	 * @param personnes the personnes
	 */
	public Projet(Integer id, String titre, List<Personne> personnes) {
		super();
		this.id = id;
		this.titre = titre;
		this.personnes = personnes;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the titre.
	 *
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Sets the titre.
	 *
	 * @param titre the new titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Gets the personnes.
	 *
	 * @return the personnes
	 */
	public List<Personne> getPersonnes() {
		return personnes;
	}

	/**
	 * Sets the personnes.
	 *
	 * @param personnes the new personnes
	 */
	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Projet [id=" + id + ", titre=" + titre + ", personnes=" + personnes + "]";
	}

}
