package webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webshop.database.entity.ReviewDAO;
import webshop.database.repository.ReviewRepository;

@SpringBootApplication
public class WebshopApplication implements CommandLineRunner {

	@Autowired
	private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ReviewDAO review = ReviewDAO.builder()
				.id(1)
				.numStar(3)
				.description("the best of the best")
				.build();

		reviewRepository.save(review);
	}

}
