package library.service;

import library.model.Author;
import library.model.Book;
import library.repository.BookRepository;
import library.util.exceptoins.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Alexander on 17.10.2017.
 */
@Service
public class BookServiceImpl implements BookService {
   @Autowired
   private BookRepository repository;
    @Autowired
    private StorageService storageService;

    @Override
    public Book save(@NotNull Book book) {
        return repository.save(book);
    }

    @Override
    public Book get(long id)throws NotFoundException {
        return repository.findOne(id);
    }

    @Override
    public void delete(long id) {
        Book book = get(id);
        repository.delete(id);
        storageService.deletePath(book.getPathCover());
    }

    @Override
    public List<Book> findByAuthor(@NotNull Author author) {
        return repository.findByAuthor(author);
    }
}
