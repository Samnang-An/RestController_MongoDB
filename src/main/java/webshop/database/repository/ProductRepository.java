package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.controller.dto.ProductDto;
import webshop.database.entity.ProductDAO;
import webshop.database.entity.ReviewDAO;

public interface ProductRepository extends MongoRepository<ProductDAO,Long> {

  ProductDAO findByProdName(String prodName);
}
