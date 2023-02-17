package com.distribuida.servicios;

import com.distribuida.db.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    Author findAuthor(Long id);

    void saveAuthor(Author author);

    void updateAuthor(long id, Author author) throws Exception;

    void deleteAuthor(Long id);

}
