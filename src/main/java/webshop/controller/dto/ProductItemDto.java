package webshop.controller.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import webshop.database.entity.ProductDAO;
import webshop.database.entity.ProductItemDAO;

@Builder
@Setter
@Getter
@Data
public class ProductItemDto {

  private long id;
  private int amount;
  private ProductDto product;

  public static List<ProductItemDto> of(List<ProductItemDAO> productItem) {
    return productItem.stream().map(ProductItemDto::of).collect(Collectors.toList());
  }

  public static ProductItemDto of(ProductItemDAO productItemDAO){
    return ProductItemDto.builder()
        .id(productItemDAO.getId())
        .amount(productItemDAO.getAmount())
        .product(ProductDto.of(productItemDAO.getProductDAO()))
        .build();
  }
}
