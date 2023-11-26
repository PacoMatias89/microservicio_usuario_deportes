package com.pacomolina.controller;


import com.pacomolina.entity.User;

import com.pacomolina.model.Sport;

import com.pacomolina.securityJwt.SecurityJwtProvider;
import com.pacomolina.service.PersonService;
import com.pacomolina.service.RolService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class PersonController {
	
	private final static Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RolService rolService;

	@Autowired
	private SecurityJwtProvider jwtProvider;

	@GetMapping("/persons")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<User>> allPersons() {

		List<User> persons = personService.getAllPerson();

		if (persons.isEmpty()) {
			// código 204
			return ResponseEntity.noContent().build();
		}

		// código 200
		return ResponseEntity.ok(persons);

	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/person/{name}")
	public ResponseEntity<Optional<User>> findPersonByName(@PathVariable("name") String name) {
		Optional<User> persons = personService.getPersonByName(name);

		if (persons.isEmpty()) {
			// código 404
			return ResponseEntity.notFound().build();
		}

		// código 200
		return ResponseEntity.ok(persons);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/sports/{userId}")
	public ResponseEntity<Map<String, Object>> getSportByUserId(@PathVariable("userId") int userId) {
		Map<String, Object> resultAllSportUserById = personService.getAllSportByUserId(userId);

		return ResponseEntity.ok(resultAllSportUserById);

	}
 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addsport/{userId}")
	public ResponseEntity<String> saveSportByPerson(@PathVariable("userId") int userId, @RequestBody Sport sport) {

		Sport sportNew = personService.saveSportByPersonId(userId, sport);

		return ResponseEntity.ok("Sport satisfactorily inserted");

	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/person/{id}")
	public ResponseEntity<User> updatePerson(@PathVariable int id, @RequestBody User person) {

		User updatedPerson = personService.personUpdateById(id, person);

		if (updatedPerson == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedPerson);

	}
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deletePerson(@PathVariable("id") int id) {
		User personDelete = personService.deletePersonById(id);

		if (personDelete == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(personDelete);
	}

}
