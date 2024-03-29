package webshop.database.entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import webshop.database.service.dto.ReviewDto;
import webshop.database.service.dto.StockDto;
import webshop.database.service.dto.SupplierDto;

@Document(collection = "product")
@Builder
@Setter
@Getter
@Data
public class ProductDAO {

  @Transient
  public static final String SEQUENCE_NAME = "users_sequence";

  @Id
  private long prodNum;
  private String prodName;
  private String description;
  private double price;

  private List<ReviewDto> reviewList;
  private StockDto stock;
  private SupplierDto supplier;

}
