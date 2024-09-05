package com.itsqmet.gamerarticles.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter
@Setter
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Hidden
	private int id; 
	
	 @Column(length=20) 
	 private String nombre;
	 
	 @Column(length=10) 
	 private String telefono;
	 
	 @ManyToOne
	 (fetch=FetchType.LAZY, optional=false)
	 private Ciudad ciudad;
	
}
