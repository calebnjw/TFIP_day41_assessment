package backend.controllers;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Book;
import backend.models.ReviewResponse;
import backend.models.SearchParameters;
import backend.repositories.BookRepository;
import backend.services.ReviewService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

@RestController
@RequestMapping(path = "/api/books")
@CrossOrigin(origins = "*")
public class BookController {

  @Autowired
  public BookRepository bookRepository;

  @Autowired
  public ReviewService reviewService;

  @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> findBooks(@RequestBody String jsonBody) {
    SearchParameters searchParameters = new SearchParameters();

    System.out.println("HELLO, SEARCHING.");

    JsonObject o = Json.createReader(new StringReader(jsonBody)).readObject();
    searchParameters.setCharacter(o.getString("character"));

    List<Book> books = this.bookRepository.getAllBooks(searchParameters);

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    for (Book b : books) {
      arrayBuilder.add(b.toSimpleJson());
    }
    JsonArray result = arrayBuilder.build();

    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
  }

  @GetMapping(path = "/get/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> getBook(@PathVariable String book_id) {
    Book book = this.bookRepository.getOneBook(book_id);

    JsonValue result = book.toJson();

    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
  }

  @GetMapping(path = "/reviews/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> getReviews(@PathVariable String title) throws IOException {
    System.out.println("GETTING REVIEWS FOR BOOK: " + title);

    ReviewResponse reviewResponse = reviewService.getReviews(title);

    if (reviewResponse.getNum_results() <= 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .contentType(MediaType.APPLICATION_JSON)
          .body(null);
    }

    JsonValue result = reviewResponse.toJson();

    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
  }

}
