package webshop.database.entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Setter
@Getter
@Data
public class ProductDAO {

  @Id
  private int prodNum;
  private String prodName;
  private String description;
  private double price;

  private List<ReviewDAO> reviewList;
  private StockDAO stock;
  private SupplierDAO supplier;

}
