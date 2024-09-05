package com.itsqmet.gamerarticles.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Embeddable
@Getter
@Setter
public class DetalleCompra {
	
	private int cantidad;
	
	@ManyToOne
	(fetch=FetchType.LAZY, optional=true)
	Producto producto;

	@Stereotype("MONEY")
	@Depends("producto.precio, cantidad")
	@ReadOnly
	public BigDecimal getSubtotal() {
		if (producto == null || producto.getPrecio() == null) {
			return BigDecimal.ZERO;
		}
		return producto.getPrecio().multiply(new BigDecimal(cantidad));
	}
}
