package webshop.database.service;

import webshop.controller.dto.ProductDto;

public interface ProductService {

  ProductDto addProduct(ProductDto productDto);
  ProductDto getProduct(long id);
  ProductDto getProduct(String name);

}
