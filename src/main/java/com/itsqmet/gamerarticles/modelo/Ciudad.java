package com.itsqmet.gamerarticles.modelo;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
public class Ciudad {
	
	@Id
    private int codigo;

    @Column(length = 25)
    private String nombre;
    
    
    @ManyToOne
    @JoinColumn(name = "provincia_id", nullable = false)  
    private Provincia provincia;
    
    /*@ManyToOne
	(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
    private Provincia provincia;*/


}
