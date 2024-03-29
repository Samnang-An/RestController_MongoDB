package webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import webshop.database.exception.NoProductFoundException;
import webshop.database.service.dto.ProductDto;
import webshop.database.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("/add-product")
  public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
    ProductDto product = productService.addProduct(productDto);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable long id) {
    ProductDto product = null;
    try {
      product = productService.getProduct((int) id);
    } catch (NoProductFoundException e) {
    }
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

}
