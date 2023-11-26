package com.pacomolina.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.pacomolina.enums.RolName;

@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolName rolName;

	public Rol() {
	}

	public Rol(@NotNull RolName rolName) {
		this.rolName = rolName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolName getRolNombre() {
		return rolName;
	}

	public void setRolNombre(RolName rolNombre) {
		this.rolName = rolNombre;
	}

}
