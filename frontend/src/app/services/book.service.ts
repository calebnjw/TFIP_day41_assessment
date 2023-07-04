import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom, lastValueFrom } from 'rxjs';

@Injectable()
export class BookService {
  constructor(private http: HttpClient) {}

  searchFor(character: string) {
    const requestBody = {
      character,
    };

    return firstValueFrom(
      this.http.post<any>('http://localhost:8080/api/books/search', requestBody)
    );
  }

  findOne(book_id: string) {
    return firstValueFrom(
      this.http.get<any>('http://localhost:8080/api/books/get/' + book_id)
    );
  }
}
