package com.evaluacion.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluacion.Dao.IProductoDao;
import com.evaluacion.Entity.Producto;

@Service
public class ProductoServicelmpl implements ProductoService {

	@Autowired
	private IProductoDao proDao;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return (List<Producto>) proDao.findAll();
	}

	@Override
	public Producto save(Producto produ) {
		// TODO Auto-generated method stub
		return proDao.save(produ);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findbyId(Long codigo) {
		// TODO Auto-generated method stub
		return proDao.findById(codigo).orElse(null);
	}

	@Override
	public void delete(Long codigo) {
		// TODO Auto-generated method stub
		proDao.deleteById(codigo);
	}

}
