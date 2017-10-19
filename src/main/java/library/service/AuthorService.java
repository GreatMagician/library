package library.service;

import library.model.Author;
import library.util.exceptoins.NotFoundException;

import java.util.List;

/**
 * Created by Alexander on 17.10.2017.
 */
public interface AuthorService {
    Author save (Author author);

    Author get (long id) throws NotFoundException;

    List<Author> getAll();
}
