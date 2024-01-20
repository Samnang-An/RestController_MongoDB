package webshop.database.service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import webshop.controller.dto.CustomerDto;
import webshop.database.entity.CustomerDAO;
import webshop.database.entity.DatabaseSequence;
import webshop.database.exception.NoCustomerFoundException;
import webshop.database.repository.CustomerRepository;
import webshop.database.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private MongoOperations mongoOperations;

  public long generateSequence(String seqName) {
    DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
        new Update().inc("seq", 1), options().returnNew(true).upsert(true),
        DatabaseSequence.class);
    return !Objects.isNull(counter) ? counter.getSeq() : 1;
  }

  @Override
  public CustomerDto save(CustomerDto customerDto) {
    CustomerDAO cus = CustomerDAO.builder()
        .id(generateSequence(CustomerDAO.SEQUENCE_NAME))
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

