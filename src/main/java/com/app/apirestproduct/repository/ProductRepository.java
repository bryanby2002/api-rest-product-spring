package com.app.apirestproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.apirestproduct.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  
}
