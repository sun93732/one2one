package market;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;

/**
 * To show how the many-to-one work
 */
public class App {
  public static void main(String[] args) throws Exception {

    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    System.out.println("Hibernate Configuration loaded");
    ServiceRegistry serviceRegistry =
                                      new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    System.out.println("Hibernate serviceRegistry created");
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    // obtains the session
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Stock stock = new Stock();
    stock.setStockCode("7052");
    stock.setStockName("PADINI");
   
    StockDailyRecord stockDailyRecord1 = new StockDailyRecord();
    stockDailyRecord1.setPriceOpen(new Float("1.2"));
    stockDailyRecord1.setPriceClose(new Float("1.1"));
    stockDailyRecord1.setPriceChange(new Float("10.0"));
    stockDailyRecord1.setVolume(3000000L);
    stockDailyRecord1.setDate(new Date());
    stockDailyRecord1.setStock(stock);
    
    
    
    StockDailyRecord stockDailyRecord2 = new StockDailyRecord();
    stockDailyRecord2.setPriceOpen(new Float("2.2"));
    stockDailyRecord2.setPriceClose(new Float("2.1"));
    stockDailyRecord2.setPriceChange(new Float("20.0"));
    stockDailyRecord2.setVolume(2000000L);
    stockDailyRecord2.setDate(new Date());
    stockDailyRecord2.setStock(stock);
    stock.getStockDailyRecords().add(stockDailyRecord1);
    stock.getStockDailyRecords().add(stockDailyRecord2);
    
    Integer stockId = (Integer) session.save(stock);
    //session.save(stockDailyRecord1);
    //session.save(stockDailyRecord2);
    
    Stock stock2  = (Stock) session.get(Stock.class, stockId);
    
    session.getTransaction().commit();
    session.close();
    sessionFactory.close();
    System.out.println("Done");
  }
}