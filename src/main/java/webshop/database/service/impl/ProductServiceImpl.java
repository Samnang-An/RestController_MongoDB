package webshop.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.database.exception.NoProductFoundException;
import webshop.database.repository.ProductRepository;
import webshop.database.service.AddNewProductService;
import webshop.database.service.ProductService;
import webshop.database.service.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private AddNewProductService addNewProductService;

  @Override
  public ProductDto addProduct(ProductDto productDto) {
    return addNewProductService.proceed(productDto);
  }


  @Override
  public ProductDto getProduct(long id) throws NoProductFoundException {
    return ProductDto.of(
        productRepository.findById(id).orElseThrow(() -> new NoProductFoundException()));
  }

  @Override
  public ProductDto getProduct(String name) {
    return ProductDto.of(productRepository.findByProdName(name));
  }
}

