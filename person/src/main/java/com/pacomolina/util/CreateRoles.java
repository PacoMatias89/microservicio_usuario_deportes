package com.pacomolina.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pacomolina.entity.Rol;
import com.pacomolina.enums.RolName;
import com.pacomolina.service.RolService;

@Component
public class CreateRoles implements CommandLineRunner{

	@Autowired
	private RolService rolService;

	@Override
	public void run(String... args) throws Exception {
		
		/*Rol admin = new Rol(RolName.ADMIN);
		Rol user = new Rol(RolName.USER);
		
		rolService.save(admin);
		rolService.save(user);*/

		
		
	}
	
	
	
}
