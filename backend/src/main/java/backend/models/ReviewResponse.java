package backend.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class ReviewResponse {
  String status;
  String copyright;
  Integer num_results;
  List<JsonObject> results = new LinkedList<JsonObject>();

  public ReviewResponse() {
  }

  public ReviewResponse(String status, String copyright, Integer num_results, List<JsonObject> results) {
    this.status = status;
    this.copyright = copyright;
    this.num_results = num_results;
    this.results = results;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public Integer getNum_results() {
    return num_results;
  }

  public void setNum_results(Integer num_results) {
    this.num_results = num_results;
  }

  public List<JsonObject> getResults() {
    return results;
  }

  public void setResults(List<JsonObject> results) {
    this.results = results;
  }

  @Override
  public String toString() {
    return "ReviewResponse [status=" + status + ", copyright=" + copyright + ", num_results=" + num_results
        + ", results=" + results.toString() + "]";
  }

  public static ReviewResponse create(String json) throws IOException {
    ReviewResponse reviewResponse = new ReviewResponse();

    try (InputStream inputStream = new ByteArrayInputStream(json.getBytes())) {
      JsonReader jsonReader = Json.createReader(inputStream);
      JsonObject responseObject = jsonReader.readObject();

      // System.out.println("NYT RESPONSE OBJECT IN JSON: " + responseObject);

      reviewResponse.setStatus(responseObject.getString("status"));
      reviewResponse.setCopyright(responseObject.getString("copyright"));
      reviewResponse.setNum_results(responseObject.getInt("num_results"));
      JsonArray jsonArray = responseObject.getJsonArray("results");

      for (int i = 0; i < jsonArray.size(); i++) {
        reviewResponse.results.add(jsonArray.get(i).asJsonObject());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    // System.out.println("COMPLETED RESPONSE OBJECT: " + reviewResponse);
    return reviewResponse;
  }

  public JsonValue toJson() {
    if (results != null) {
      JsonArrayBuilder resultsArrayBuilder = Json.createArrayBuilder();
      results.forEach((JsonValue review) -> resultsArrayBuilder.add(review));
      JsonArray resultsArray = resultsArrayBuilder.build();

      return Json.createObjectBuilder()
          .add("num_results", getNum_results())
          .add("results", resultsArray)
          .build();
    }

    return Json.createObjectBuilder()
        .add("num_results", getNum_results())
        .add("results", "[]")
        .build();
  }
}
