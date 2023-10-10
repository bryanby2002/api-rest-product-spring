package com.app.apirestproduct.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {
  
  private Product product;
  private String message;


}
