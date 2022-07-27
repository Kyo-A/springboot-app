package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class Personne.
 */
@Entity
public class Personne {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The nom. */
	private String nom;

	/** The prenom. */
	private String prenom;

	/** The age. */
	private int age;

	// CascadeType.PERSIST : lors de la persistance d'une personne, conserve
	// également voitures.
	// CascadeType.REMOVE : lors de la suppression d'une personne, il supprime
	// également
	// les entités voitures.
	// CascadeType.REFRESH : lors de l'actualisation d'une personne, actualise
	// également les entités
	// contenues dans voitures.
	// CascadeType.MERGE : lors de la fusion de l'état de l'entité personne,
	/** The voitures. */
	// fusionne également les entités contenues dans voitures.
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Voiture> voitures = new ArrayList<>();

	/** The projets. */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "personnes_projets", joinColumns = { @JoinColumn(name = "id_personne") }, inverseJoinColumns = {
			@JoinColumn(name = "id_projet") })
	public List<Projet> projets = new ArrayList<>();

	/**
	 * Instantiates a new personne.
	 */
	public Personne() {
		super();
	}

	/**
	 * Instantiates a new personne.
	 *
	 * @param nom    the nom
	 * @param prenom the prenom
	 * @param age    the age
	 */
	public Personne(String nom, String prenom, int age) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	/**
	 * Instantiates a new personne.
	 *
	 * @param nom the nom
	 * @param prenom the prenom
	 * @param age the age
	 * @param voitures the voitures
	 * @param projets the projets
	 */
	public Personne(String nom, String prenom, int age, List<Voiture> voitures, List<Projet> projets) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.voitures = voitures;
		this.projets = projets;
	}

	/**
	 * Instantiates a new personne.
	 *
	 * @param id the id
	 * @param nom the nom
	 * @param prenom the prenom
	 * @param age the age
	 */
	public Personne(Integer id, String nom, String prenom, int age) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
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
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Sets the nom.
	 *
	 * @param nom the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets the prenom.
	 *
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Sets the prenom.
	 *
	 * @param prenom the new prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the voitures.
	 *
	 * @return the voitures
	 */
	public List<Voiture> getVoitures() {
		return voitures;
	}

	/**
	 * Sets the voitures.
	 *
	 * @param voitures the new voitures
	 */
	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}

	/**
	 * Gets the projets.
	 *
	 * @return the projets
	 */
	public List<Projet> getProjets() {
		return projets;
	}

	/**
	 * Sets the projets.
	 *
	 * @param projets the new projets
	 */
	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", voitures=" + voitures
				+ ", projets=" + projets + "]";
	}

}
