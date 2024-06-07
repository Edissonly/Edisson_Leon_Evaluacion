package com.evaluacion.Dao;

import org.springframework.data.repository.CrudRepository;
import com.evaluacion.Entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}