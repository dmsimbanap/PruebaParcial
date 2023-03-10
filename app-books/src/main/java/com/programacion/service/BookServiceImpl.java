package com.programacion.service;

import com.programacion.dto.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @PersistenceContext(unitName = "pu1")
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() throws ExecutionException, InterruptedException {
//        Multi<DbRow> execute = this.dbClient
//                .execute(exe -> exe.createQuery("SELECT a.id as author_id, a.first_name, a.last_name, b.id, b.title, b.isbn, b.price FROM book b JOIN author a ON b.author_id = a.id").execute());
//        return execute.collectList().get().stream().map(this.bookMapper::read).collect(Collectors.toList());
        return entityManager.createNamedQuery("Book.findAll").getResultList();
    }

    @Override
    public Book findOne(long id) throws ExecutionException, InterruptedException {
//        Optional<DbRow> dbRow = this.dbClient
//                .execute(exe -> exe.createGet("SELECT a.id as author_id, a.first_name, a.last_name, b.id, b.title, b.isbn, b.price FROM book b JOIN author a ON b.author_id = a.id WHERE b.id = :id").addParam("id", id).execute()).get();
//
//        return dbRow.isPresent() ? this.bookMapper.read(dbRow.get()) : new Book();
        return (Book) entityManager.createNamedQuery("Book.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional
    public void save(Book book) {
//        var rowsChanged = 0L;
//        try {
//            rowsChanged = this.dbClient
//                    .execute(exec -> exec
//                            .insert("INSERT INTO book (author_id, isbn, title, price) VALUES(?, ?, ?, ?)",
//                                    book.getAuthor().getId(), book.getIsbn(), book.getTitle(), book.getPrice()
//                            )).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        this.entityManager.persist(book);
//        return rowsChanged;
    }

    @Override
    @Transactional
    public void update(long id, Book book) {
//        var rowsChanged = 0L;
//        try {
//            rowsChanged = this.dbClient
//                    .execute(exec -> exec
//                            .update("UPDATE book SET author_id = ?, isbn = ?, title = ?, price = ? WHERE id = ?",
//                                    book.getAuthor().getId(), book.getIsbn(), book.getTitle(), book.getPrice(), id
//                            )).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rowsChanged;
        var oldBook = this.entityManager.find(Book.class, id);
        oldBook.setAuthor(book.getAuthor());
        oldBook.setIsbn(book.getIsbn());
        oldBook.setTitle(book.getTitle());
        oldBook.setPrice(book.getPrice());
        this.entityManager.merge(oldBook);
    }

    @Override
    @Transactional
    public void delete(long id) {
//        var rowsChanged = 0L;
//        try {
//            rowsChanged = this.dbClient
//                    .execute(exec -> exec
//                            .delete("DELETE FROM book WHERE id = ?",
//                                    id
//                            )).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rowsChanged;
        var query = this.entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
