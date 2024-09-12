package com.itsqmet.gamerarticles.modelo;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

import lombok.*;

@Entity
@Getter
@Setter
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Hidden
	private int id; 
	
	 @Column(length=25)
	 @Size(min=7, max=25)
	 private String nombre;
	 
	 @Column(length=10) 
	 private String telefono;
	 
	 @ManyToOne
	 (fetch=FetchType.LAZY, optional=false)
	 private Ciudad ciudad;
	 
	 @PrePersist
	 @PreUpdate
	    private void validarNombreUnico() {
	        // Verificar si el nombre ya existe en la base de datos
	        EntityManager em = XPersistence.getManager();
	        Query query = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.nombre = :nombre AND c.id != :id");
	        query.setParameter("nombre", this.nombre);
	        query.setParameter("id", this.id != 0 ? this.id : -1); // Si es nuevo, id es -1

	        Long count = (Long) query.getSingleResult();

	        if (count > 0) {
	            throw new ValidationException("El nombre ya está registrado.");
	        }
	    }
	
}
