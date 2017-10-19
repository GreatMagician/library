package library.service;

import library.model.Book;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 17.10.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {
    @Autowired
    private BookService service;
    @Autowired
    private AuthorService authorService;

    @Test
    public void save() throws Exception {
        Book book = newBook();
        service.save(book);
    }

    private Book newBook(){
        Book book = new Book();
        book.setName("testName");
        book.setAuthor(authorService.get(10));
        return book;
    }

    @Test
    public void get() throws Exception {
        System.out.println(service.get(11).toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(12);
    }

    @Test
    public void findByAuthor() throws Exception {
        List<Book> books = service.findByAuthor(authorService.get(10));
        books.forEach(book -> System.out.println(book.toString()));
    }

}