package com.pacomolina.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.pacomolina.FeignClient.SportFeignClient;
import com.pacomolina.entity.User;
import com.pacomolina.model.Sport;
import com.pacomolina.repository.PersonRespository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRespository usuarioRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SportFeignClient sportFeignClient;



	public List<User> getAllPerson() {
		return usuarioRepository.findAll();
	}

	public User getPersonById(int userId) {
		return usuarioRepository.findById(userId).orElse(null);
	}

	public Optional<User> getPersonByName(String name) {

		return  usuarioRepository.findByusername(name);
	}
	
	
	   public Optional<User> getByUserName(String userName){
	        return usuarioRepository.findByusername(userName);
	    }

	    public boolean existsByUserName(String userName){
	        return usuarioRepository.existsByusername(userName);
	    }

	    public boolean existsByEmail(String email){
	        return usuarioRepository.existsByEmail(email);
	    }

	    public void save(User user){
	        usuarioRepository.save(user);
	    }
	

	public User personUpdateById(int id, User updatePerson) {
		User existPerson = usuarioRepository.findById(id).orElse(null);

		if (existPerson != null) {
			existPerson.setName(updatePerson.getName());
			existPerson.setUserName(updatePerson.getUserName());
			existPerson.setEmail(updatePerson.getEmail());
			// Puedes actualizar más campos aquí según sea necesario
			usuarioRepository.save(existPerson); // Guardar los cambios en la base de datos
		}

		return existPerson;

	}

	


	public User deletePersonById(int id) {
		// Buscar la persona en la base de datos por el ID
		User existDeletePerson = usuarioRepository.findById(id).orElse(null);

		if (existDeletePerson != null) {
			// Si la persona existe, eliminarla de la base de datos
			usuarioRepository.delete(existDeletePerson);
		}

		return existDeletePerson;
	}

	// Comumicación entre microservicios
	public List<Sport> getSports(long userId) {
		List<Sport> sports = restTemplate.getForObject("http://sport-server/sport/user/" + userId, List.class);

		return sports;
	}

	// Conectamos person con sport para la creación de deportes a traves de person

	public Sport saveSportByPersonId(int id, Sport sport) {

		// Verificar si el usuario con el ID proporcionado existe

		User user = getPersonById(id);

		if (user == null) {
			String errorMessage = "El usuario con ID " + id + " no existe.";
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
		}

		sport.setUserId(id);
		Sport newSport = sportFeignClient.save(sport);

		// Si el deporte se ha guardado exitosamente en el otro servicio,
		// también podemos guardarlo en la base de datos local si es necesario
		return newSport;
	}

	public Map<String, Object> getAllSportByUserId(int id) {
		Map<String, Object> resultSport = new HashMap<>();

		Optional<User> person = usuarioRepository.findById(id);

		if (person.isEmpty()) {
			resultSport.put("Message", "User not found");
			return resultSport;
		} else {
			resultSport.put("Person", person);
		}

		List<Sport> sports = sportFeignClient.getSportByUserId(id);

		if (sports == null) {
			resultSport.put("Sports", "User has not registered activities");
		} else {
			resultSport.put("Sports", sports);
		}
		return resultSport;

	}
	
	

}
