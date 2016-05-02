package dao;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import pl.bookstore.robot.pojo.BookStore;

import java.util.ArrayList;
import java.util.List;

public class BookStoreDAO extends DAO {
	
	public static BookStoreDAO getInstance() {
		return new BookStoreDAO();
	}

	private BookStoreDAO() {
	}

public void saveOrUpdate(BookStore bookStore) {
		try {
			beginTransaction();
			getSession().saveOrUpdate(bookStore);
			commitTransaction();
		} catch (HibernateException e) {
			System.out
					.println("BookStoreDAO couldn't saveOrUpdate a BookStore.");
			rollback();
		}
	}

	public void persist(BookStore bookStore) {
		try {
			beginTransaction();
			getSession().persist(bookStore);
			commitTransaction();
		} catch (HibernateException e) {
			System.out
					.println("BookStoreDAO couldn't persiste a BookStore.");
			rollback();
		}
	}

	public BookStore get(int id) {
		BookStore bookStore = null;
		try {
			beginTransaction();
			bookStore = (BookStore) getSession().get(BookStore.class, id);
			commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		    	System.out.println("BookStoreDAO couldn't get a BookStore.");
			rollback();
		}
		return bookStore;
	}

	public List<BookStore> getAll() {
		List<BookStore> ParkingSpaces = new ArrayList<BookStore>();
		try {
			beginTransaction();
			Query ParkingSpaceQuery = getSession().createQuery(
					"from "+BookStore.class.getSimpleName());
			ParkingSpaces = (List<BookStore>) ParkingSpaceQuery.list();
			commitTransaction();
		} catch (HibernateException e) {
			rollback();
			System.out.println("BookStoreDAO couldn't get All BookStore");
		}
		return ParkingSpaces;

	}

}
