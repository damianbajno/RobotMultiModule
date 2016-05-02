package dao;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import pl.bookstore.robot.pojo.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DAO {

    public void persist(Book book) {
	try {
	    beginTransaction();
	    getSession().persist(book);
	    commitTransaction();
	} catch (HibernateException e) {
	    rollback();
	    System.out.println("BookDao Couldn't persist");
	}

    }

    public void delate(Book book) {
	try {
	    beginTransaction();
	    getSession().delete(book);
	    commitTransaction();
	} catch (HibernateException e) {
	    rollback();
	    System.out.println("BookDao Couldn't delete");
	}
    }

    public void update(Book book) {
	try {
	    beginTransaction();
	    getSession().update(book);
	    commitTransaction();
	} catch (HibernateException e) {
	    rollback();
	    System.out.println("BookDao Couldn't update");
	}
    }

    public static List<Book> getAll() {
	List<Book> clients = new ArrayList<Book>();
	try {
	    beginTransaction();
	    Query BookQuery = getSession().createQuery(
		    "Select b from Book b");
	    clients = (List<Book>) BookQuery.list();
	    commitTransaction();
	} catch (HibernateException e) {
	    rollback();
	    System.out.println("BookDao rollback get all clients");
	}
	return clients;
    }

    public Book load(Long id) {
	Book Book = null;
	try {
	    beginTransaction();
	    Book = (Book) getSession().load(Book.class, id);
	    commitTransaction();
	} catch (HibernateException e) {
	    rollback();
	    System.out.println("BookDAO couldn't load book");
	}
	return Book;
    }

    public Book get(int index) {
	Book book = null;
	try {
	    beginTransaction();
	    book = (Book) getSession().get(Book.class, index);
	    commitTransaction();
	} catch (HibernateException e) {
	    rollback();
	    System.out.println("BookDao couldn't get Book");
	}
	return book;
    }

}
