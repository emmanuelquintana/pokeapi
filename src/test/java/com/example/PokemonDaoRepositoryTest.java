package com.example;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PokemonDaoRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	PokemonRepository pokemonRepository;

	@Autowired
	TypeRepository typeRepository;

	@Test
	public void savePikachu() {

		Pokemon pikachu = new Pokemon();
		pikachu.setNombre("pikachu");
		Type electric = new Type(1, "electric");
		Type ground = new Type(2, "ground");
		ArrayList<Type> grounds = new ArrayList<>();
		grounds.add(ground);
		pikachu.setTipos(electric);
		pikachu.setDebilidades(grounds);
		pikachu.setImage("TODO");
		entityManager.persist(pikachu);
		pikachu.setPokemonId(1);

		assertEquals(pikachu, pokemonRepository.findByNombre("pikachu"));

	}

}
