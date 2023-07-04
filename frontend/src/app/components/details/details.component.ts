import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css'],
  providers: [BookService],
})
export class DetailsComponent implements OnInit {
  constructor(
    private bookService: BookService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  book_id!: string;
  book!: Book;

  ngOnInit(): void {
    this.book_id = this.activatedRoute.snapshot.params['book_id'];
    console.log('BOOK ID: ', this.book_id);

    this.bookService
      .findOne(this.book_id)
      .then((res) => {
        this.book = res;
      })
      .catch((error) => {
        console.log(error.message);
      });
  }

  goBack() {
    this.router.navigate(['/search/', this.book.title.substring(0, 1)]);
  }

  findReviews(title: string) {
    this.router.navigate(['/reviews/', this.book_id], {
      queryParams: { title: this.book.title },
    });
  }
}
