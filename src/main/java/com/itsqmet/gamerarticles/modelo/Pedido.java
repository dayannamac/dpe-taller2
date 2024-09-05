package com.itsqmet.gamerarticles.modelo;

import java.math.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter
@Setter

@View(
		members="cliente;"
		+ "detallesCompra;"
		+ "total;"
        + "totalConDescuento;"
        + "transporte;"
        + "precioFinal"
		)
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Hidden
	private int id;
	
	@OneToOne
	(fetch=FetchType.LAZY, optional=false)
	private Cliente cliente;
	
	@ElementCollection
	@ListProperties("producto.id, producto.nombre, producto.imagen, producto.precio, cantidad, subtotal")
	Collection<DetalleCompra> detallesCompra;
	
	@Stereotype("MONEY")
	@Depends("detallesCompra.subtotal")
	@ReadOnly
	public BigDecimal getTotal() {
		return detallesCompra.stream()
			.map(DetalleCompra::getSubtotal)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	@Stereotype("MONEY")
	@Depends("total")
	@ReadOnly
	public BigDecimal getTotalConDescuento() {
		BigDecimal total = getTotal();
		BigDecimal descuento;

		if (total.compareTo(new BigDecimal(400)) >= 0) {
			descuento = new BigDecimal("0.50");
		} else if (total.compareTo(new BigDecimal(300)) >= 0) {
			descuento = new BigDecimal("0.40");
		} else if (total.compareTo(new BigDecimal(200)) >= 0) {
			descuento = new BigDecimal("0.30");
		} else if (total.compareTo(new BigDecimal(100)) >= 0) {
			descuento = new BigDecimal("0.15");
		} else {
			descuento = new BigDecimal("0.03");
		}

		BigDecimal totalConDescuento = total.subtract(total.multiply(descuento));
		return totalConDescuento;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transporte_id")
	private Transporte transporte;
	
	@Stereotype("MONEY")
	@Depends("totalConDescuento, transporte.precioTarifa")
	@ReadOnly
	public BigDecimal getPrecioFinal() {
		BigDecimal totalConDescuento = getTotalConDescuento();
		BigDecimal tarifaTransporte = transporte != null ? transporte.getPrecioTarifa() : BigDecimal.ZERO;
		return totalConDescuento.add(tarifaTransporte);
	}
	
}
