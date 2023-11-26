package com.pacomolina.tipodeportes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pacomolina.tipodeportes.entity.Sport;
import com.pacomolina.tipodeportes.repository.SportRepository;

@Service
public class SportService {

	List<Sport> sports = new ArrayList<Sport>();

	@Autowired
	private SportRepository sportRepository;

	public List<Sport> getAllSports() {
		return sportRepository.findAll();
	}

	public List<Sport> getSportByType(String type) {
		List<Sport> sportType = sportRepository.findByType(type);

		return sportType;
	}

	public Sport createSport(Sport sport) {
		Sport newSport = sportRepository.save(sport);

		return newSport;
	}

	public Sport sportUpdateById(long id, Sport sportUpdate) {
		  // Buscar el deporte en la base de datos por el ID
        Sport sportExist = sportRepository.findById(id).orElse(null);

        if (sportExist != null) {
            sportExist.setSport(sportUpdate.getSport());
            sportExist.setType(sportUpdate.getType());
            // Puedes actualizar más campos aquí según sea necesario
            sportRepository.save(sportExist); // Guardar los cambios en la base de datos
        }

        return sportExist;

	}

	public Sport deleteSportById(long id) {

		 // Buscar el deporte en la base de datos por el ID
        Sport existDeleteSport = sportRepository.findById(id).orElse(null);

        if (existDeleteSport != null) {
            // Si el deporte existe, eliminarlo de la base de datos
            sportRepository.delete(existDeleteSport);
        }

        return existDeleteSport;
	}
	
	public List<Sport> getSportByUserId(int id){
		return sportRepository.findByUserId(id);
	}

}
