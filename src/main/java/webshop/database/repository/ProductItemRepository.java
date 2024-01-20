package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.CustomerDAO;
import webshop.database.entity.ProductItemDAO;

public interface ProductItemRepository extends MongoRepository<ProductItemDAO,Long> {

}
