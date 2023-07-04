import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable()
export class ReviewService {
  constructor(private http: HttpClient) {}

  findReviews(title: string) {
    let spaceFreeTitle: string = title.replaceAll(' ', '%20');
    let completeUrl: string =
      'https://api.nytimes.com/svc/books/v3/reviews.json?title=' +
      spaceFreeTitle +
      'api-key=ma1E79wgyOGYnFDXx4gEtvuHgDq3NNEs';

    console.log(completeUrl);

    return firstValueFrom(
      this.http.get<any>(
        'https://api.nytimes.com/svc/books/v3/reviews.json?title=' +
          spaceFreeTitle +
          '&api-key=ma1E79wgyOGYnFDXx4gEtvuHgDq3NNEs'
      )
    );
  }
}
