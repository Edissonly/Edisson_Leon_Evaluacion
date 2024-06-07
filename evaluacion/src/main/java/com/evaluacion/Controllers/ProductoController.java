package com.evaluacion.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.evaluacion.Entity.Producto;
import com.evaluacion.Services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductoController {
	@Autowired
	private ProductoService productoService;

	@GetMapping("/producto")
	public List<Producto> getAllProductos() {
		return productoService.findAll();
	}

	@GetMapping("producto/{id}")
	public Producto getProductoById(@PathVariable Long id) {
		Producto producto = productoService.findbyId(id);
		if (producto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
		}
		return producto;
	}

	@PostMapping("/producto")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto createProducto(@Valid @RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> update(@RequestBody Producto actividad, @PathVariable Long id) {
		Producto listaActual = productoService.findbyId(id);

		if (listaActual == null) {
			return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
		}
		if (actividad.getCodigo() != null && !actividad.getCodigo().equals(listaActual.getCodigo())) {
			return new ResponseEntity<>("No se puede modificar el nombre de la lista de reproducción",
					HttpStatus.CONFLICT);
		}
		listaActual.setDescripcion(actividad.getDescripcion());
		productoService.save(listaActual);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Borrar_Datos //
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Producto listaActual = productoService.findbyId(id);
		if (listaActual == null) {
			return new ResponseEntity<>("Producto no existe", HttpStatus.NOT_FOUND);
		}
		productoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
