package hb;

import java.util.Date;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class BooksManager {

  public static void main(String[] args) {

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

    // creates a Book entity
    Book newBook = new Book();
    newBook.setTitle("Effective Java");
    newBook.setDescription("Best practices for Java programming");
    newBook.setPublishedDate(new Date());
    newBook.setAuthor(new Author("Joshua Bloch", "joshua.bloch@gmail.com"));
    // persists the book entity
    Long bookId = (Long) session.save(newBook);
    
    // gets the book entity back
    Book book = (Book) session.get(Book.class, bookId);
    System.out.println("Title: " + book.getTitle());
    System.out.println("Description: " + book.getTitle());

    Author author = book.getAuthor();
    System.out.println("Author's name: " + author.getName());
    System.out.println("Author's email: " + author.getEmail());

    
    session.getTransaction().commit();
    session.close();
    ;
  }
}