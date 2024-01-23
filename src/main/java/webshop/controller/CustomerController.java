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
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.service.CustomerService;
import webshop.database.service.dto.CustomerDto;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/create-new")
  public ResponseEntity<?> createNewCustomer(@RequestBody CustomerDto customerDto) {
    CustomerDto customer = customerService.save(customerDto);
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @GetMapping("/{cusId}")
  public ResponseEntity<?> getCustomer(@PathVariable long cusId) {
    CustomerDto customerDto = null;
    try {
      customerDto = customerService.findCustomer(cusId);
    } catch (NoCustomerFoundException e) {
    }
    return new ResponseEntity<>(customerDto, HttpStatus.OK);
  }

}
