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
        console.log('RESULT: ', res);
        this.book = res;
        console.log('BOOK!', this.book);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }
}
