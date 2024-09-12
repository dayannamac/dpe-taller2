package com.itsqmet.gamerarticles.modelo;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Getter
@Setter
public class Ciudad {
	
	@Id
    private int codigo;

    @Column(length = 25)
    @NotNull
    @Size(min=5, max=25)
    @Pattern(regexp = "^[A-Z][a-z]+$", 
    message = "El nombre de la ciudad debe comenzar con una letra mayúscula y no contener números ni caracteres especiales.")
    private String nombre;
    
    
    @ManyToOne
    @JoinColumn(name = "provincia_id", nullable = false)  
    private Provincia provincia;
    
    /*@ManyToOne
	(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
    private Provincia provincia;*/


}
