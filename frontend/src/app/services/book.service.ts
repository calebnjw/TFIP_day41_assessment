import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom, lastValueFrom } from 'rxjs';

@Injectable()
export class BookService {
  constructor(private http: HttpClient) {}

  searchFor(character: string, page: number) {
    console.log('CHARACTER: ', character);
    console.log('PAGE: ', page);

    const requestBody = {
      character,
      page,
    };

    return firstValueFrom(
      this.http.post<any>('http://localhost:8080/api/books/search', requestBody)
    );
  }
}
