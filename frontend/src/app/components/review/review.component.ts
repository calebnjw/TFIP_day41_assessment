import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book, ReviewResponse } from 'src/app/models';
import { BookService } from 'src/app/services/book.service';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css'],
  providers: [BookService, ReviewService],
})
export class ReviewComponent implements OnInit {
  constructor(
    private bookService: BookService,
    private reviewService: ReviewService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  book_id!: string;
  title!: string;
  reviews!: ReviewResponse;

  ngOnInit(): void {
    this.book_id = this.activatedRoute.snapshot.params['book_id'];
    this.title = this.activatedRoute.snapshot.queryParams['title'];
    console.log('TITLE: ', this.title);

    this.reviewService
      .findReviews(this.title)
      .then((res) => {
        console.log('NYTIMES RESPONSE: ', res);
        this.reviews = res;
        console.log(this.reviews);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }

  goBack() {
    this.router.navigate(['/book/', this.book_id]);
  }
}
