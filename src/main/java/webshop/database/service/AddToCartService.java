package webshop.database.service;

import webshop.database.service.dto.ShoppingCartDto;
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.exception.NoProductFoundException;

public interface AddToCartService {

  ShoppingCartDto addToCart(long customerId, long productId, int amount)
      throws NoProductFoundException, NoCustomerFoundException;
}
