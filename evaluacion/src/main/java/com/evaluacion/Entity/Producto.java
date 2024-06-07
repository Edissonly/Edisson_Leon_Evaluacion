package com.evaluacion.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@SuppressWarnings("deprecation")
@Entity
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotNull
	@Size(max = 100)
	private String descripcion;

	@NotNull
	@DecimalMin("0.01")
	@Digits(integer = 10, fraction = 2)
	private BigDecimal precio;

	@NotNull
	@Min(1)
	private Integer cantidad;

	public BigDecimal getValorCompra() {
		return precio.multiply(new BigDecimal(cantidad));
	}

	public BigDecimal getDescuento() {
		BigDecimal valorCompra = getValorCompra();
		if (valorCompra.compareTo(new BigDecimal("50")) > 0) {
			return valorCompra.multiply(new BigDecimal("0.10"));
		} else {
			return BigDecimal.ZERO;
		}
	}

	public BigDecimal getIva() {
		return getValorCompra().subtract(getDescuento()).multiply(new BigDecimal("0.12"));
	}

	public BigDecimal getTotal() {
		return getValorCompra().subtract(getDescuento()).add(getIva());
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
