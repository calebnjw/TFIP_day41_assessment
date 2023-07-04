package backend.models;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Book {
  private String book_id;
  private String title;
  private List<String> authors;
  private String description;
  private Integer pages;
  private Float rating;
  private Integer rating_count;
  private String genres;
  private String image_url;

  public Book() {
  }

  public Book(String book_id, String title) {
    this.book_id = book_id;
    this.title = title;
  }

  public Book(String book_id, String title, List<String> authors, String description, Integer pages, Float rating,
      Integer rating_count, String genres, String image_url) {
    this.book_id = book_id;
    this.title = title;
    this.authors = authors;
    this.description = description;
    this.pages = pages;
    this.rating = rating;
    this.rating_count = rating_count;
    this.genres = genres;
    this.image_url = image_url;
  }

  public String getBookId() {
    return book_id;
  }

  public void setBookId(String book_id) {
    this.book_id = book_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public Integer getRatingCount() {
    return rating_count;
  }

  public void setRatingCount(Integer rating_count) {
    this.rating_count = rating_count;
  }

  public String getGenres() {
    return genres;
  }

  public void setGenres(String genres) {
    this.genres = genres;
  }

  public String getImageURL() {
    return image_url;
  }

  public void setImageURL(String image_url) {
    this.image_url = image_url;
  }

  @Override
  public String toString() {
    return "Book [book_id=" + book_id + ", title=" + title + ", authors=" + authors + ", description=" + description
        + ", pages="
        + pages + ", rating=" + rating + ", ratingCount=" + rating_count + ", genres=" + genres + ", image_url="
        + image_url
        + "]";
  }

  public static Book createBasic(SqlRowSet rs) {
    Book book = new Book();

    book.setBookId(rs.getString("book_id"));
    book.setTitle(rs.getString("title"));

    return book;
  }

  public static Book createFull(SqlRowSet rs) {
    Book book = new Book();

    String authorString = rs.getString("authors");
    System.out.println(authorString);

    book.setBookId(rs.getString("book_id"));
    book.setTitle(rs.getString("title"));
    // book.setAuthors(rs.getString("authors"));
    book.setDescription(rs.getString("description"));
    book.setPages(rs.getInt("pages"));
    book.setRating(rs.getFloat("rating"));
    book.setRatingCount(rs.getInt("rating_count"));
    book.setGenres(rs.getString("genres"));
    book.setImageURL(rs.getString("image_url"));

    return book;
  }

  public JsonValue toSimpleJson() {
    return Json.createObjectBuilder()
        .add("book_id", getBookId())
        .add("title", getTitle())
        .build();
  }

  public JsonValue toJson() {
    return Json.createObjectBuilder()
        .add("book_id", getBookId())
        .add("title", getTitle())
        .add("authors", getAuthors().toString())
        .add("description", getDescription())
        .add("pages", getPages())
        .add("rating", getRating())
        .add("rating_count", getRatingCount())
        .add("genres", getGenres())
        .add("image_url", getImageURL())
        .build();
  }

}
