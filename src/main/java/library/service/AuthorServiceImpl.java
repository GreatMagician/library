package library.service;

import library.model.Author;
import library.repository.AuthorRepository;
import library.util.exceptoins.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Alexander on 17.10.2017.
 */
@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository repository;

    @Override
    public Author save(@NotNull Author author) {
        return repository.save(author);
    }

    @Override
    public Author get(long id) throws NotFoundException {
        return repository.findOne(id);
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }
}
