package com.app.apirestproduct.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.apirestproduct.model.Product;
import com.app.apirestproduct.model.ProductResponse;
import com.app.apirestproduct.repository.ProductRepository;

@RestController
@RequestMapping
public class ProductController {
  
  @Autowired
  private ProductRepository productRepository;

  // -> get all products
  @GetMapping("/products")
  public List<Product> getAll() {
    return this.productRepository.findAll();
  }

  // -> get product by id
  @GetMapping("products/{id}")
  public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id){
    Optional<Product> product = this.productRepository.findById(id);
    if(product.isPresent()){
      return ResponseEntity.ok(product.get());
    } else {
      String message = "Producto con id["+id+"] no encontrado";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
  }

  // -> new product
  @PostMapping("/save")
  public ResponseEntity<Object> newProduct(@RequestBody Product product) {
    this.productRepository.save(product);
    String message = "Producto registrado correctamente";
    return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(product,message));
  }

  // -> update product
  @PutMapping("update/{id}")
  public ResponseEntity<Object> updateProductById(@PathVariable("id") Integer id, @RequestBody Product product) {
    if(!this.productRepository.existsById(id)){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No exite un producto con id"+id);
    } else {
      product.setId(id);
      product = this.productRepository.save(product);
      return ResponseEntity.ok(product);
    }
  }

  // -> delete product
  @DeleteMapping("delete/{id}")
  public ResponseEntity<Object> deleteProductoById(@PathVariable("id") Integer id) {
    if(!this.productRepository.existsById(id)){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con id="+id);
    } else {
      this.productRepository.deleteById(id);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body("Producto con id="+id+" eliminado");
    }
  }

}
