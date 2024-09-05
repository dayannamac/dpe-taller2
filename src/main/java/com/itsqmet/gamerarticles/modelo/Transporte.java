package com.itsqmet.gamerarticles.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter
@Setter
public class Transporte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Hidden
	private int id;

	@Column(length=25)
	private String nombre;
	
	@Money
	private BigDecimal precioTarifa;
	
}
