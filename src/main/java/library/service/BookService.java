package library.service;

import library.model.Author;
import library.model.Book;
import library.util.exceptoins.NotFoundException;

import java.util.List;

/**
 * Created by Alexander on 17.10.2017.
 */
public interface BookService {

    Book save (Book book);

    Book get (long id) throws NotFoundException;

    void delete (long id);

    // найти все книги автора
    List<Book> findByAuthor (Author author);

}
