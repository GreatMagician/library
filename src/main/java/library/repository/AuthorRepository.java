package library.repository;

import library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alexander on 17.10.2017.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
