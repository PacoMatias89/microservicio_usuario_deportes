package com.pacomolina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pacomolina.entity.Rol;
import com.pacomolina.enums.RolName;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	Optional<Rol> findByRolName(RolName rolName);

}
