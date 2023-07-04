package backend.services;

import org.springframework.stereotype.Service;

import backend.models.ReviewResponse;

@Service
public class ReviewService {
  public ReviewResponse getReviews(String title) {
    String apiUrl = "https://api.nytimes.com/svc/books/v3/reviews.json?api-key=ma1E79wgyOGYnFDXx4gEtvuHgDq3NNEs&title="
        + title;

    // RequestEntity req = RequestEntity.get(apiUrl);
    return null;
  }
}
