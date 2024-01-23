package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.ProductDAO;

public interface ProductRepository extends MongoRepository<ProductDAO,Long> {

  ProductDAO findByProdName(String prodName);
}
