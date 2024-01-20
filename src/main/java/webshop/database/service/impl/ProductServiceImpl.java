package webshop.database.service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Collections;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import webshop.database.entity.DatabaseSequence;
import webshop.controller.dto.ProductDto;
import webshop.database.entity.ProductDAO;
import webshop.database.entity.StockDAO;
import webshop.database.entity.SupplierDAO;
import webshop.database.repository.ProductRepository;
import webshop.database.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @Override
  public ProductDto addProduct(ProductDto productDto) {
    ProductDAO product = ProductDAO.builder()
        .prodNum(generateSequence(ProductDAO.SEQUENCE_NAME))
        .prodName(productDto.getProdName())
        .description(productDto.getDescription())
        .price(productDto.getPrice())
        .stock(StockDAO.builder()
            .id(generateSequence(StockDAO.SEQUENCE_NAME))
            .amount(productDto.getStock().getAmount())
            .warehouse(productDto.getStock().getWarehouse())
            .build())
        .supplier(SupplierDAO.builder()
            .id(generateSequence(SupplierDAO.SEQUENCE_NAME))
            .supplierName(productDto.getSupplier().getSupplierName())
            .build())
        .reviewList(Collections.EMPTY_LIST)
        .build();
    return ProductDto.of(productRepository.save(product));
  }


  @Override
  public ProductDto getProduct(long id) {
    return ProductDto.of(productRepository.findById(id).get());
  }

  @Override
  public ProductDto getProduct(String name) {
    return ProductDto.of(productRepository.findByProdName(name));
  }

  public long generateSequence(String seqName) {
    DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
        new Update().inc("seq", 1), options().returnNew(true).upsert(true),
        DatabaseSequence.class);
    return !Objects.isNull(counter) ? counter.getSeq() : 1;
  }
}

