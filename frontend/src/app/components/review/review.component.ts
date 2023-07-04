import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css'],
  providers: [ReviewService],
})
export class ReviewComponent implements OnInit {
  constructor(
    private reviewService: ReviewService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  book_id!: string;

  ngOnInit(): void {
    this.book_id = this.activatedRoute.snapshot.params['book_id'];
    console.log('BOOK ID: ', this.book_id);

    //   this.reviewService
    //     .findReviews(this.book_id)
    //     .then((res) => {
    //       this.book = res;
    //     })
    //     .catch((error) => {
    //       console.log(error.message);
    //     });
  }
}
