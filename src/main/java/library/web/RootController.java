package library.web;

import library.model.Author;
import library.model.Book;
import library.service.AuthorService;
import library.service.BookService;
import library.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Alexander on 17.10.2017.
 */
@Controller
public class RootController {
    private static final Logger LOG = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/")
    public String root(ModelMap model) {
        return "index";
    }

    // Загрузить всех авторов
    @RequestMapping(value = "/getAuthors", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody  List<Author> getAuthors (){
        return authorService.getAll();
    }

    //получить книги автора
    @RequestMapping(value = "/getBooks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody  List<Book> getBooks (@RequestParam Long id){
        return bookService.findByAuthor(authorService.get(id));
    }


    @RequestMapping(value = "/seeBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody  Book seeBook (@RequestParam Long id){
        return bookService.get(id);
    }


    @PutMapping(value = "/editBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Book book) {
       if (bookService.save(book) == null)
           return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
       else
           return new ResponseEntity<String>("true",HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody  boolean deleteBook (@RequestParam Long id){
        bookService.delete(id);
        return true;
    }

    @RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
    public String addAuthor(@Valid @ModelAttribute Author newAuthor, ModelMap model){
        authorService.save(newAuthor);
        return "index";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute Book newBook, @RequestParam Long id, ModelMap model){
        Author author = authorService.get(id);
        newBook.setAuthor(author);
        bookService.save(newBook);
        return "index";
    }

    // загрузить обложку для книги
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces={"application/text; charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("uploadfile") MultipartFile uploadfile) {
        String filepath = null;
        try {
            filepath = storageService.uploadFile(uploadfile);
        }
        catch (Exception e) {
            LOG.error("не удалось сохранить файл", e);
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(filepath, HttpStatus.OK);
    }
}
