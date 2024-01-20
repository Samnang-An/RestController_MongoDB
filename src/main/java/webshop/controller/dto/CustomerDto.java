package webshop.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import webshop.database.entity.CustomerDAO;

@Builder
@Setter
@Getter
public class CustomerDto {

  private long id;
  private String customerName;

  public static CustomerDto of(CustomerDAO customerDAO) {
    return CustomerDto.builder()
        .id(customerDAO.getId())
        .customerName(customerDAO.getCustomerName())
        .build();
  }
}
