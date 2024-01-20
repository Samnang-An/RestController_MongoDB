package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.ReviewDAO;

public interface StockRepository extends MongoRepository<ReviewDAO,Integer> {

}
