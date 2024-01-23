package webshop.database.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.database.service.dto.ProductDto;
import webshop.database.service.dto.ProductItemDto;
import webshop.database.service.dto.ShoppingCartDto;
import webshop.database.entity.CustomerDAO;
import webshop.database.entity.ProductDAO;
import webshop.database.entity.ShoppingCartDAO;
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.exception.NoProductFoundException;
import webshop.database.repository.CustomerRepository;
import webshop.database.repository.ProductRepository;
import webshop.database.repository.ShoppingCartRepository;
import webshop.database.service.AddToCartService;
import webshop.database.service.SequenceGenerator;

@Service
public class AddToCartServiceImpl implements AddToCartService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private ShoppingCartRepository shoppingCartRepository;
  @Autowired
  private SequenceGenerator sequenceGenerator;

  public ShoppingCartDto addToCart(long customerId, long productId, int amount)
      throws NoProductFoundException, NoCustomerFoundException {
    CustomerDAO customerDao = customerRepository.findById(customerId)
        .orElseThrow(() -> new NoCustomerFoundException());
    ProductDto productDto = ProductDto.of(productRepository.findById(productId)
        .orElseThrow(() -> new NoProductFoundException()));
    ShoppingCartDAO shoppingCartDAO = shoppingCartRepository.findShoppingCartDAOByCustomerDAO(
        customerDao);
    if (Objects.isNull(shoppingCartDAO)) {
      shoppingCartDAO = ShoppingCartDAO.builder()
          .id(sequenceGenerator.generate(ShoppingCartDAO.SEQUENCE_NAME))
          .customerDAO(customerDao)
          .build();
    }
    ProductItemDto productItem = ProductItemDto.builder()
        .amount(amount)
        .product(productDto)
        .build();
    List<ProductItemDto> productItems = Optional.ofNullable(shoppingCartDAO.getProductItem())
        .orElse(new ArrayList<>());
    productItems.add(productItem);
    shoppingCartDAO.setProductItem(productItems);
    return ShoppingCartDto.of(shoppingCartRepository.save(shoppingCartDAO));
  }
}
