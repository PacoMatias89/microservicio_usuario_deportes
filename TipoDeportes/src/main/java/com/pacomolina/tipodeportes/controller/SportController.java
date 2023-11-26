package com.pacomolina.tipodeportes.controller;


import com.pacomolina.tipodeportes.entity.Sport;
import com.pacomolina.tipodeportes.service.SportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport")
public class SportController {


    @Autowired
    private SportService sportService;


    @GetMapping("/sports")
    public ResponseEntity<List<Sport>> allSports(){
    	List<Sport> sports = sportService.getAllSports();

		if (sports.isEmpty()) {
			// código 204
			return ResponseEntity.noContent().build();
		}

		// código 200
		return ResponseEntity.ok(sports);

    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Sport>> findByType(@PathVariable("type") String type) {
    	
    	List<Sport> sports = sportService.getSportByType(type);

		if (sports.isEmpty()) {
			// código 404
			return ResponseEntity.notFound().build();
		}

		// código 200
		return ResponseEntity.ok(sports);
	}    
	
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Sport>> findSportUserById(@PathVariable("userId") int id){
		
		List<Sport> sports = sportService.getSportByUserId(id);
		if (sports.isEmpty()) {
			// obtenemos el código 204
			return ResponseEntity.noContent().build();
		}
		// obtenemos el código 200
		return ResponseEntity.ok(sports);
		
	}
    
    

    @PostMapping
    public ResponseEntity<Sport> addSport(@RequestBody Sport sport) {
       Sport newSport = sportService.createSport(sport);
       
       return ResponseEntity.ok(newSport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sport> updatePerson(@PathVariable Long id, @RequestBody Sport sport) {

    	Sport updatedSport = sportService.sportUpdateById(id, sport);

		if (updatedSport == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedSport);

	}


    @DeleteMapping("/{id}")
    public ResponseEntity<Sport> deletePerson(@PathVariable("id") Long id) {
    	Sport sportDelete = sportService.deleteSportById(id);

		if (sportDelete == null) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(sportDelete);
	}


}
