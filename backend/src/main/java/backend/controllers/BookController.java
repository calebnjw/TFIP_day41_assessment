package backend.controllers;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import backend.models.Book;
import backend.models.SearchParameters;
import backend.repositories.BookRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/api/books")
@CrossOrigin(origins = "*")
public class BookController {

  @Autowired
  public BookRepository bookRepository;

  @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> findBooks(@RequestBody String jsonBody) {
    SearchParameters searchParameters = new SearchParameters();

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

}
