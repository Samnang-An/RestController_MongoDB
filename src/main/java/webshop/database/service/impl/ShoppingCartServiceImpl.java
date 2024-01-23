package webshop.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.database.service.dto.ShoppingCartDto;
import webshop.database.entity.CustomerDAO;
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.exception.NoProductFoundException;
import webshop.database.repository.CustomerRepository;
import webshop.database.repository.ShoppingCartRepository;
import webshop.database.service.AddToCartService;
import webshop.database.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private ShoppingCartRepository shoppingCartRepository;
  @Autowired
  private AddToCartService addToCartService;


  @Override
  public ShoppingCartDto addToCart(long customerId, long productId, int amount)
      throws NoCustomerFoundException, NoProductFoundException {
    return addToCartService.addToCart(customerId, productId, amount);
  }

  @Override
  public ShoppingCartDto getShoppingCart(long customerId) throws NoCustomerFoundException {
    CustomerDAO customerDao = customerRepository.findById(customerId)
        .orElseThrow(() -> new NoCustomerFoundException());
    return ShoppingCartDto.of(shoppingCartRepository.findShoppingCartDAOByCustomerDAO(customerDao));
  }
}

