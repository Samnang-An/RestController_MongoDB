package webshop.database.service;

import webshop.database.service.dto.ProductDto;

public interface AddNewProductService {

  ProductDto proceed(ProductDto productDto);
}
