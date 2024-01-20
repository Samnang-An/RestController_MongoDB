package webshop.database.service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import webshop.controller.dto.CustomerDto;
import webshop.controller.dto.ProductDto;
import webshop.controller.dto.ShoppingCartDto;
import webshop.database.entity.CustomerDAO;
import webshop.database.entity.DatabaseSequence;
import webshop.database.entity.ProductDAO;
import webshop.database.entity.ProductItemDAO;
import webshop.database.entity.ShoppingCartDAO;
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.exception.NoProductFoundException;
import webshop.database.repository.CustomerRepository;
import webshop.database.repository.ProductRepository;
import webshop.database.repository.ShoppingCartRepository;
import webshop.database.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  @Autowired
  private MongoOperations mongoOperations;

  public long generateSequence(String seqName) {
    DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
        new Update().inc("seq", 1), options().returnNew(true).upsert(true),
        DatabaseSequence.class);
    return !Objects.isNull(counter) ? counter.getSeq() : 1;
  }

  @Override
  public ShoppingCartDto addToCart(long customerId, long productId, int amount)
      throws NoCustomerFoundException, NoProductFoundException {
    CustomerDAO customerDao = customerRepository.findById(customerId)
        .orElseThrow(() -> new NoCustomerFoundException());
    ProductDAO productDao = productRepository.findById(productId)
        .orElseThrow(() -> new NoProductFoundException());
    ShoppingCartDAO shoppingCartDAO = shoppingCartRepository.findShoppingCartDAOByCustomerDAO(
        customerDao);
    if (Objects.isNull(shoppingCartDAO)) {
      shoppingCartDAO = ShoppingCartDAO.builder()
          .id(generateSequence(ShoppingCartDAO.SEQUENCE_NAME))
          .customerDAO(customerDao)
          .build();
    }
    ProductItemDAO productItem = ProductItemDAO.builder()
        .id(generateSequence(ProductItemDAO.SEQUENCE_NAME))
        .amount(amount)
        .productDAO(productDao)
        .build();
    List<ProductItemDAO> productItems = Optional.ofNullable(shoppingCartDAO.getProductItem())
        .orElse(new ArrayList<>());
    productItems.add(productItem);
    shoppingCartDAO.setProductItem(productItems);
    return ShoppingCartDto.of(shoppingCartRepository.save(shoppingCartDAO));
  }

  @Override
  public ShoppingCartDto getShoppingCart(long customerId) throws NoCustomerFoundException {
    CustomerDAO customerDao = customerRepository.findById(customerId)
        .orElseThrow(() -> new NoCustomerFoundException());
    return ShoppingCartDto.of(shoppingCartRepository.findShoppingCartDAOByCustomerDAO(customerDao));
  }
}

