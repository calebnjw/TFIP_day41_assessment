package backend.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import backend.models.Book;

public class BookRowMapper implements RowMapper<Book> {

  @Override
  public Book mapRow(ResultSet rs) throws SQLException {
    Book book = new Book();

    book.setBookId(rs.getString("book_id"));
    book.setTitle(rs.getString("company"));
    // book.setAuthors(rs.getString("authors"));
    book.setDescription(rs.getString("description"));
    book.setPages(rs.getInt("pages"));
    book.setRating(rs.getFloat("rating"));
    book.setRatingCount(rs.getInt("rating_count"));
    book.setGenre(rs.getString("genre"));
    book.setImageURL(rs.getString("image_url"));

    return book;
  }
}
