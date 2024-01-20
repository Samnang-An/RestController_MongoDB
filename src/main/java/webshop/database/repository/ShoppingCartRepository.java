package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.CustomerDAO;
import webshop.database.entity.ProductItemDAO;
import webshop.database.entity.ShoppingCartDAO;
import webshop.database.service.ShoppingCartService;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartDAO,Long> {

  ShoppingCartDAO findShoppingCartDAOByCustomerDAO(CustomerDAO customerDAO);

}
