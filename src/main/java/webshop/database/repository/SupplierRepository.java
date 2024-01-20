package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.SupplierDAO;

public interface SupplierRepository extends MongoRepository<SupplierDAO,Long> {

}
