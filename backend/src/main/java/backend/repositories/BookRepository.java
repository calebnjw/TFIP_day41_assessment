package backend.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import backend.models.Book;
import backend.models.SearchParameters;

// import backend.repositories.Queries.*;

@Repository
public class BookRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  public static final String SELECT_ALL_BOOKS = "SELECT book_id, title FROM book2018 WHERE title LIKE ? ORDER BY title";

  public static final String SELECT_BOOK = "SELECT * FROM book2018 WHERE book_id = ?";

  public List<Book> getAllBooks(SearchParameters searchParameters) {
    String character = searchParameters.getCharacter() + "%";

    List<Book> books = new ArrayList<Book>();

    SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ALL_BOOKS, character);
    while (rs.next()) {
      books.add(Book.createBasic(rs));
    }

    System.out.println(books);
    return books;
  }

  public Book getOneBook(String book_id) {
    List<Book> books = new ArrayList<Book>();

    SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_BOOK, book_id);
    System.out.println("RESULTS!" + rs.toString());

    while (rs.next()) {
      // System.out.println(rs.next());
      books.add(Book.createFull(rs));
    }

    System.out.println(books.get(0));
    return books.get(0);
  }
}
