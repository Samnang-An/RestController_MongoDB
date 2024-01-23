package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.exception.NoProductFoundException;
import webshop.database.service.ShoppingCartService;
import webshop.database.service.dto.ShoppingCartDto;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService shoppingCartService;

  @PostMapping("/add/{cusId}/{prodId}/{amount}")
  public ResponseEntity<?> addProduct(@PathVariable long cusId, @PathVariable long prodId,
      @PathVariable int amount) {
    ShoppingCartDto shoppingCartDto = null;
    try {
      shoppingCartDto = shoppingCartService.addToCart(cusId, prodId, amount);
    } catch (NoCustomerFoundException | NoProductFoundException e) {
    }
    return new ResponseEntity<>(shoppingCartDto, HttpStatus.OK);
  }

  @GetMapping("/{cusId}")
  public ResponseEntity<?> getShoppingCart(@PathVariable long cusId) {
    ShoppingCartDto shoppingCartDto = null;
    try {
      shoppingCartDto = shoppingCartService.getShoppingCart(cusId);
    } catch (NoCustomerFoundException e) {
    }
    return new ResponseEntity<>(shoppingCartDto, HttpStatus.OK);
  }

}
