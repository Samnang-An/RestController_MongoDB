package webshop.database.entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import webshop.database.service.dto.ProductItemDto;

@Document(collection = "shopping-cart")
@Builder
@Setter
@Getter
@Data
public class ShoppingCartDAO {

  @Transient
  public static final String SEQUENCE_NAME = "shopping_cart_sequence";

  @Id
  private long id;
  private CustomerDAO customerDAO;
  private List<ProductItemDto> productItem;
}
