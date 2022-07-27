package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dao.PersonneRepository;
import com.example.demo.models.Personne;

// TODO: Auto-generated Javadoc
/**
 * The Class RunnerConfig.
 */
@Component
public class RunnerConfig implements CommandLineRunner {

	/** The personne repository. */
	@Autowired
	private PersonneRepository personneRepository;

	/**
	 * Run.
	 *
	 * @param args the args
	 * @throws Exception the exception
	 */
	@Override
	public void run(String... args) throws Exception {
		personneRepository.save(new Personne("NOM1", "PRENOM1", 40));
		personneRepository.save(new Personne("NOM2", "PRENOM2", 30));
		personneRepository.save(new Personne("NOM3", "PRENOM3", 30));
	}

}
