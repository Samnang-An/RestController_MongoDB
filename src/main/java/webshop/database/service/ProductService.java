package webshop.database.service;

import webshop.database.exception.NoProductFoundException;
import webshop.database.service.dto.ProductDto;

public interface ProductService {

  ProductDto addProduct(ProductDto productDto);

  ProductDto getProduct(long id) throws NoProductFoundException;

  ProductDto getProduct(String name);

}
