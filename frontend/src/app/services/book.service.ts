import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class BookService {
  constructor(private httpClient: HttpClient) {}

  lookupCharacter(character: string) {}
}
