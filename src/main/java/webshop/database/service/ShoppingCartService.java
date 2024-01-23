package webshop.database.service;

import webshop.database.exception.NoCustomerFoundException;
import webshop.database.exception.NoProductFoundException;
import webshop.database.service.dto.ShoppingCartDto;

public interface ShoppingCartService {

  ShoppingCartDto addToCart(long customerId, long productId, int amount)
      throws NoCustomerFoundException, NoProductFoundException;

  ShoppingCartDto getShoppingCart(long customerId) throws NoCustomerFoundException;

}
