package webshop.database.service.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import webshop.database.entity.ShoppingCartDAO;

@Builder
@Setter
@Getter
public class ShoppingCartDto {

  private long id;
  private CustomerDto customer;
  private List<ProductItemDto> productItem;

  public static ShoppingCartDto of(ShoppingCartDAO shoppingCartDAO) {
    return ShoppingCartDto.builder()
        .id(shoppingCartDAO.getId())
        .customer(CustomerDto.of(shoppingCartDAO.getCustomerDAO()))
        .productItem(shoppingCartDAO.getProductItem())
        .build();
  }
}
