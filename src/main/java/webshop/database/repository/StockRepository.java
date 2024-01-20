package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.ReviewDAO;
import webshop.database.entity.StockDAO;

public interface StockRepository extends MongoRepository<StockDAO,Long> {

}
