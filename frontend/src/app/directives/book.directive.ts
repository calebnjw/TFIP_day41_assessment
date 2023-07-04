import { Directive } from '@angular/core';
import { Book } from '../models';

@Directive({
  selector: '[appBook]',
})
export class BookDirective {
  constructor() {}

  book!: Book;
}
