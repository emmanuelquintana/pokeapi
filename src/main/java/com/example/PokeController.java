package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PokeController {
	ArrayList<Pokemon> pokes = new ArrayList<>();

	@Autowired
	PokemonDao pokemonDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/pokemon")
	@ResponseBody

	public ResponseEntity<ArrayList<Pokemon>> pokemon(@RequestParam(value = "name", required = false) String name) {

		if (name == null) {

			return ResponseEntity.ok(pokemonDao.getPokes());

		}
		boolean b = Pattern.matches(".*\\d.*", name);
		if (b) {
			String sb = "no puede haber numeros en el nombre";
			return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);

		}
		if (pokemonDao.getPokes(name) == null) {
			String sb = "no existe dicho pokemon<br><br>" + "*recomendación*<br>" + "-revise la escritura<br>";
			return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(pokemonDao.getPokes(name));

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/pokemon/id")
	@ResponseBody

	public ResponseEntity<ArrayList<Pokemon>> pokemonID(
			@RequestParam(value = "id", required = false) String pokemonId) {
		if (pokemonId == null) {
			String sb = "debe digitar un ID para buscar el pokemon<br>escriba en la URL lo siguiente:<br>?id=X<br><br>donde x es la ID del pokemon que quiere buscar<br>0 para todos los pokes";
			return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);

		}

		if (Integer.parseInt(pokemonId) < 0 || Integer.parseInt(pokemonId) > 151) {
			String sb = "no existe dicho tipo <br>use un id entre 1 y 151 (inclusivo)<br> 0 para todos los pokemones";
			return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(pokemonDao.getPokes((Integer.parseInt(pokemonId))));
	}


	//abilietes
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/pokemon/abilites")
	@ResponseBody

	public ResponseEntity<ArrayList<Pokemon>> pokemonAbilites(
			@RequestParam(value = "abilites", required = false) String abilites) {
		if (abilites == null) {
			String sb = "debe digitar una abilidad para buscar el pokemon<br>escriba en la URL lo siguiente:<br>?abilites=X<br><br>donde x es la abilidad del pokemon que quiere buscar<br>0 para todos los pokes";
			return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);

		}

		if (pokemonDao.getPokes(abilites) == null) {
			String sb = "no existe dicho tipo <br>use una abilidad valida<br> 0 para todos los pokemones";
			return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(pokemonDao.getPokes(abilites));
	}

	//held-items

@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("/pokemon/held-items")
@ResponseBody
public ResponseEntity<List<HeldItem>> heldItems(@RequestParam(value = "name", required = false) String heldItemName) {

	if (heldItemName == null) {

		return ResponseEntity.ok(pokemonDao.getHeldItems());

	}
	if (pokemonDao.getHeldItems(heldItemName) == null) {
		String sb = "no existe dicho item<br>" + "*recomendación*<br>" + "-revise la escritura<br>";
		return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);
	} else
		return ResponseEntity.ok(pokemonDao.getHeldItems(heldItemName));

}

//base-experiences

@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("/pokemon/base-experiences")
@ResponseBody
public ResponseEntity<List<BaseExperience>> baseExperiences(
		@RequestParam(value = "name", required = false) String baseExperienceName) {

	if (baseExperienceName == null) {

		return ResponseEntity.ok(pokemonDao.getBaseExperiences());

	}
	if (pokemonDao.getBaseExperiences(baseExperienceName) == null) {
		String sb = "no existe dicho item<br>" + "*recomendación*<br>" + "-revise la escritura<br>";
		return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);
	} else
		return ResponseEntity.ok(pokemonDao.getBaseExperiences(baseExperienceName));

		//location-area-encounters
		@SuppressWarnings("unchecked","rawtypes")
		@RequestMapping("/pokemon/location-area-encounters")
		@ResponseBody
		public ResponseEntity<List<LocationAreaEncounter>> locationAreaEncounters(
				@RequestParam(value = "name", required = false) String locationAreaEncounterName) {

			if (locationAreaEncounterName == null) {

				return ResponseEntity.ok(pokemonDao.getLocationAreaEncounters());

			}
			if (pokemonDao.getLocationAreaEncounters(locationAreaEncounterName) == null) {
				String sb = "no existe dicho item<br>" + "*recomendación*<br>" + "-revise la escritura<br>";
				return new ResponseEntity(sb, HttpStatus.BAD_REQUEST);
			} else
				return ResponseEntity.ok(pokemonDao.getLocationAreaEncounters(locationAreaEncounterName));

			

}


