package webshop.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import webshop.database.entity.SupplierDAO;

@Builder
@Setter
@Getter
@Data
public class SupplierDto {

  private long id;
  private int supplierNum;
  private String supplierName;

  public static SupplierDto of(SupplierDAO supplierDAO){
    return SupplierDto.builder()
        .id(supplierDAO.getId())
        .supplierNum(supplierDAO.getSupplierNum())
        .supplierName(supplierDAO.getSupplierName())
        .build();
  }
  public static SupplierDto createNewSupplier(String supplierName) {
    return SupplierDto.builder()
        .supplierName(supplierName)
        .build();
  }
}
