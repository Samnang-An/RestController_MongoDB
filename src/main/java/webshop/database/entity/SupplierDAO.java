package webshop.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "supplier")
@Builder
@Setter
@Getter
@Data
public class SupplierDAO {

  @Transient
  public static final String SEQUENCE_NAME = "supplier_sequence";

  @Id
  private long id;
  private int supplierNum;
  private String supplierName;

}
