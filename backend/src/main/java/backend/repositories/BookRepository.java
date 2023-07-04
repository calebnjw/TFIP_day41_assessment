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

  public static final String SELECT_BOOK = "SELECT book_id, title, authors, description, pages, rating, rating_count, genres, image_url AS coverURL FROM book2018 WHERE book_id = ?";

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
}
