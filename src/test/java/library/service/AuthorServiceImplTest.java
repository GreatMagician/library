package library.service;

import library.model.Author;
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
public class AuthorServiceImplTest {
    @Autowired
    private AuthorService service;

    @Test
    public void save() throws Exception {
        Author author = new Author();
        author.setName("testName");
        author.setFamily("testFamily");
        service.save(author);
    }

    @Test
    public void get() throws Exception {
        Author author = service.get(10);
        System.out.println(author.toString());
    }

    @Test
    public void getAll() throws Exception {
        List<Author> authors = service.getAll();
        authors.forEach(author -> System.out.println(author.toString()));
    }

}