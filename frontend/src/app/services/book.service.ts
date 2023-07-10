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
      this.http.post<any>(
        // 'https://day41assessment-production.up.railway.app/api/books/search',
        'http://localhost:8080/api/books/search',
        requestBody
      )
    );
  }

  findOne(book_id: string) {
    return firstValueFrom(
      this.http.get<any>(
        // 'https://day41assessment-production.up.railway.app/api/books/get/' +
        'http://localhost:8080/api/books/get/' + book_id
      )
    );
  }
}
