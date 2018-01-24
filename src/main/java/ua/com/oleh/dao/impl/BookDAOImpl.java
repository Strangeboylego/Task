package ua.com.oleh.dao.impl;

import org.springframework.stereotype.Repository;
import ua.com.oleh.dao.BookDAO;
import ua.com.oleh.entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class BookDAOImpl implements BookDAO {
    @PersistenceContext
    private EntityManager entityManager;
    public void save(Book book) {
        entityManager.persist(book);
    }

    public void delete(String name) {
       entityManager.remove(getBookByName(name));
    }
    public void delete(int id) {
        entityManager.remove(getBookById(id));
    }

    public void update(String name, String newname) {
        getBookByName(name).setName(newname);
    }

    public void update(int id, String newname) {
        getBookById(id).setName(newname);
    }

    private Book getBookByName(String name) {
        return  (Book)entityManager.createQuery("from Book b where b.name = '"+name+"'").getSingleResult();
    }
    private Book getBookById(int id) {
        return  (Book)entityManager.createQuery("from Book b where b.id = "+id+"").getSingleResult();
    }
    public List<Book> findAll() {
        return entityManager.createQuery("from Book  b").getResultList();
    }

    public List<Book> findAllByName(String name) {
        return entityManager.createQuery("from  Book b where b.name = '"+name+"'").getResultList();
    }
}
