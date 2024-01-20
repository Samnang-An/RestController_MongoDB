package webshop.database.service;

import webshop.controller.dto.ProductDto;
import webshop.database.exception.NoProductFoundException;

public interface ProductService {

  ProductDto addProduct(ProductDto productDto);
  ProductDto getProduct(long id) throws NoProductFoundException;
  ProductDto getProduct(String name);

}
