package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.CustomerDAO;
import webshop.database.entity.ProductDAO;

public interface CustomerRepository extends MongoRepository<CustomerDAO,Long> {

  CustomerDAO findByCustomerName(String customerName);

}
