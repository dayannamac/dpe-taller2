package com.itsqmet.gamerarticles.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter
@Setter
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Hidden
	private int id;

	@Column(length=25)
	private String nombre;
	
	/*@OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
	@ListProperties("codigo, nombre") 
	private List<Ciudad> ciudades;*/
	
	/*@ManyToOne
	(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
    private Ciudad ciudad;*/

}
