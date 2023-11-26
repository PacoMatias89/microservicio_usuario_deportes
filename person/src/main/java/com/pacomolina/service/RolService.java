package com.pacomolina.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pacomolina.entity.Rol;
import com.pacomolina.enums.RolName;
import com.pacomolina.repository.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRepository;
	
	
	public Optional<Rol> getByRolName(RolName rolName){
		return rolRepository.findByRolName(rolName);
	}
	
	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}
