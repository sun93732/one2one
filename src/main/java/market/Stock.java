package market;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stock {
  private Integer Id;
  private String stockCode;
  private String stockName;
  private Set stockDailyRecords = new HashSet();
 
  public Integer getId() {
    return Id;
  }
  
  public Set getStockDailyRecords() {
    return stockDailyRecords;
  }

  public void setStockDailyRecords(Set stockDailyRecords) {
    this.stockDailyRecords = stockDailyRecords;
  }

  public void setId(Integer id) {
    Id = id;
  }
  public String getStockCode() {
    return stockCode;
  }
  public void setStockCode(String stockCode) {
    this.stockCode = stockCode;
  }
  public String getStockName() {
    return stockName;
  }
  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

}
