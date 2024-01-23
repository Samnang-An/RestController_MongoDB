package webshop.database.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@Data
public class StockDto {

  private int amount;
  private String warehouse;
  private String locationCode;

  public static StockDto createNewStock(int amount, String warehouse,String locationCode) {
    return StockDto.builder()
        .amount(amount)
        .warehouse(warehouse)
        .locationCode(locationCode)
        .build();
  }
}
