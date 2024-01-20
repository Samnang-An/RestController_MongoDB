package webshop.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import webshop.database.entity.ReviewDAO;

@Setter
@Getter
@Builder
public class ReviewDto {

  private long id;
  private int numStar;
  private String description;

  public static ReviewDto of(ReviewDAO reviewDAO){
    return ReviewDto.builder()
        .id(reviewDAO.getId())
        .description(reviewDAO.getDescription())
        .numStar(reviewDAO.getNumStar())
        .build();
  }

}
