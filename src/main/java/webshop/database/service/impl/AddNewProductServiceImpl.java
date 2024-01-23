package webshop.database.service.impl;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.database.service.dto.ProductDto;
import webshop.database.entity.ProductDAO;
import webshop.database.repository.ProductRepository;
import webshop.database.service.AddNewProductService;
import webshop.database.service.SequenceGenerator;

@Service
public class AddNewProductServiceImpl implements AddNewProductService {

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private SequenceGenerator sequenceGenerator;

  @Override
  public ProductDto proceed(ProductDto productDto) {
    ProductDAO product = ProductDAO.builder()
        .prodNum(sequenceGenerator.generate(ProductDAO.SEQUENCE_NAME))
        .prodName(productDto.getProdName())
        .description(productDto.getDescription())
        .price(productDto.getPrice())
        .stock(productDto.getStock())
        .supplier(productDto.getSupplier())
        .reviewList(productDto.getReviewList())
        .build();
    return ProductDto.of(productRepository.save(product));
  }
}
