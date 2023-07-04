import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { book } from 'src/app/models';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
  providers: [BookService],
})
export class ListComponent implements OnInit {
  constructor(
    private bookService: BookService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  character!: string;
  page: number = 1;
  startIndex: number = (this.page - 1) * 10;
  endIndex: number = this.page * 10;
  hasPrevious: boolean = false;
  hasNext: boolean = false;

  books!: book[];
  booksDisplay!: book[];

  ngOnInit(): void {
    this.character = this.activatedRoute.snapshot.params['character'];
    // this.page = this.activatedRoute.snapshot.queryParams['page'];

    this.bookService
      .searchFor(this.character, this.page)
      .then((res) => {
        this.books = res;
        this.booksDisplay = res.slice(this.startIndex, this.endIndex);

        console.log(this.booksDisplay);

        if (this.books.length > 10) {
          this.hasNext = true;
        }
      })
      .catch((error) => {
        console.log(error.message);
      });
  }

  goBack() {
    this.router.navigate(['/']);
  }

  // this doesn't work. I need directives.
  previousPage() {
    this.page -= 1;
    console.log(this.page);
    this.booksDisplay = this.books.slice(this.startIndex, this.endIndex);
    if (this.page <= 1) {
      this.hasPrevious = false;
    }
  }

  nextPage() {
    this.page += 1;
    console.log(this.page);
    this.booksDisplay = this.books.slice(this.startIndex, this.endIndex);
    if (this.page * 10 > this.books.length) {
      this.hasNext = true;
    }
  }
}
