package webshop.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import webshop.database.entity.StockDAO;

@Builder
@Setter
@Getter
@Data
public class StockDto {

  private long id;
  private int amount;
  private String warehouse;
  private String locationCode;

  public static StockDto of(StockDAO stockDAO){
    return StockDto.builder()
        .id(stockDAO.getId())
        .amount(stockDAO.getAmount())
        .warehouse(stockDAO.getWarehouse())
        .locationCode(stockDAO.getLocationCode())
        .build();
  }

  public static StockDto createNewStock(int amount, String warehouse,String locationCode) {
    return StockDto.builder()
        .amount(amount)
        .warehouse(warehouse)
        .locationCode(locationCode)
        .build();
  }
}
