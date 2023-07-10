package backend.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import backend.models.ReviewResponse;

@Service
public class ReviewService {
  @Value("${review.api.key}")
  private String reviewApiKey;

  String REVIEW_API_URL = "https://api.nytimes.com/svc/books/v3/reviews.json";

  public ReviewResponse getReviews(String title) throws IOException {

    String finalApiUrl = UriComponentsBuilder
        .fromUriString(REVIEW_API_URL)
        .queryParam("api-key", reviewApiKey)
        .queryParam("title", title)
        .toUriString();

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> resp = template.exchange(finalApiUrl, HttpMethod.GET, null, String.class);

    ReviewResponse reviewResponse = ReviewResponse.create(resp.getBody());
    return reviewResponse;
  }
}
