package webshop.database.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
