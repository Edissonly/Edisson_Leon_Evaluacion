package com.evaluacion.Services;

import java.util.List;

import com.evaluacion.Entity.Producto;

public interface ProductoService {
	
	public List<Producto> findAll();

	public Producto save(Producto produ);

	public Producto findbyId(Long codigo);

	public void delete(Long codigo);
	
}
