package webshop.database.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@Data
public class SupplierDto {

  private int supplierNum;
  private String supplierName;

  public static SupplierDto createNewSupplier(String supplierName) {
    return SupplierDto.builder()
        .supplierName(supplierName)
        .build();
  }
}
