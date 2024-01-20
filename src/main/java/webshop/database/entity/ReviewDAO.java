package webshop.database.entity;

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
public class ReviewDAO {

  @Id
  private int id;
  private int numStar;
  private String description;
}
