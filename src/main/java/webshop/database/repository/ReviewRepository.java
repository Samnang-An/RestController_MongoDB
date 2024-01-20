package webshop.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.database.entity.ReviewDAO;

public interface ReviewRepository extends MongoRepository<ReviewDAO,Integer> {

}
