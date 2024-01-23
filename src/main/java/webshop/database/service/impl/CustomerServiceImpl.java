package webshop.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.database.service.dto.CustomerDto;
import webshop.database.entity.CustomerDAO;
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.repository.CustomerRepository;
import webshop.database.service.CustomerService;
import webshop.database.service.SequenceGenerator;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private SequenceGenerator sequenceGenerator;

  @Override
  public CustomerDto save(CustomerDto customerDto) {
    CustomerDAO cus = CustomerDAO.builder()
        .id(sequenceGenerator.generate(CustomerDAO.SEQUENCE_NAME))
        .customerName(customerDto.getCustomerName())
        .build();
    return CustomerDto.of(customerRepository.save(cus));
  }

  @Override
  public CustomerDto findCustomer(long id) throws NoCustomerFoundException {
    return CustomerDto.of(
        customerRepository.findById(id).orElseThrow(() -> new NoCustomerFoundException()));
  }

  @Override
  public CustomerDto findCustomerByName(String name) {
    return CustomerDto.of(customerRepository.findByCustomerName(name));
  }
}

