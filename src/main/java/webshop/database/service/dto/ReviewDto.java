package webshop.database.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReviewDto {

  private int numStar;
  private String description;

}
