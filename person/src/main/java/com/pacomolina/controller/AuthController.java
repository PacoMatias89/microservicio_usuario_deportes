package com.pacomolina.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.pacomolina.dto.JwtDto;
import com.pacomolina.dto.LoginPerson;
import com.pacomolina.dto.Message;
import com.pacomolina.dto.NewPerson;
import com.pacomolina.entity.User;
import com.pacomolina.entity.Rol;
import com.pacomolina.enums.RolName;
import com.pacomolina.securityJwt.SecurityJwtProvider;
import com.pacomolina.service.PersonService;
import com.pacomolina.service.RolService;


import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PersonService userService;

	@Autowired
	RolService rolService;

	@Autowired
	SecurityJwtProvider jwtProvider;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/register")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewPerson newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		if (userService.existsByUserName(newUser.getUsername()))
			return new ResponseEntity(new Message("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (userService.existsByEmail(newUser.getEmail()))
			return new ResponseEntity(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
		
		User user = new User(newUser.getName(), newUser.getUsername(),
				newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolName(RolName.USER).get());
		if (newUser.getRoles().contains("admin")) {
			roles.add(rolService.getByRolName(RolName.ADMIN).get());
		}

		user.setRoles(roles);
		userService.save(user);
		return new ResponseEntity(new Message("usuario guardado"), HttpStatus.CREATED);
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginPerson loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToke(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
	
   

}
