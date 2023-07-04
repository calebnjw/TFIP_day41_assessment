import { Directive } from '@angular/core';
import { Book } from '../models';

@Directive({
  selector: '[appList]',
})
export class ListDirective {
  constructor() {}

  books!: Book[];
  booksDisplay!: Book[];
}
