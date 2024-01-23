package webshop.database.service.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import webshop.database.entity.ProductDAO;

@Builder
@Setter
@Getter
@Data
public class ProductDto {

  private long prodNum;
  private String prodName;
  private String description;
  private double price;

  private List<ReviewDto> reviewList = new ArrayList<>();
  private StockDto stock;
  private SupplierDto supplier;

  public static ProductDto of(ProductDAO productDAO) {
    if(Objects.isNull(productDAO)){
      return null;
    }
    return ProductDto.builder()
        .prodNum(productDAO.getProdNum())
        .prodName(productDAO.getProdName())
        .description(productDAO.getDescription())
        .price(productDAO.getPrice())
        .reviewList(productDAO.getReviewList())
        .stock(productDAO.getStock())
        .supplier(productDAO.getSupplier())
        .build();
  }

  public static ProductDto createNewProduct(String prodName, String description, double price,
      int amount, String warehouse,
      String locationCode, String supplierName) {
    return ProductDto.builder()
        .prodName(prodName)
        .description(description)
        .price(price)
        .stock(StockDto.createNewStock(amount, warehouse, locationCode))
        .supplier(SupplierDto.createNewSupplier(supplierName))
        .build();
  }

  @Override
  @SneakyThrows
  public String toString() {
    ObjectMapper objectMapper = new ObjectMapper();
    return "Product Items: {" + objectMapper.writeValueAsString(this) + "}";
  }
}
