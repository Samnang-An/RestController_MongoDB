package webshop.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock")
@Builder
@Setter
@Getter
@Data
public class StockDAO {

  @Transient
  public static final String SEQUENCE_NAME = "stock_sequence";

  @Id
  private long id;
  private String locationCode;
  private int amount;
  private String warehouse;

}
