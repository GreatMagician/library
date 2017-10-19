package library.repository;

import library.model.Author;
import library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // найти все книги автора
    List<Book> findByAuthor (Author author);
}
