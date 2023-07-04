export interface Book {
  book_id: string;
  title: string;
  authors: string[];
  description: string;
  pages: number;
  rating: number;
  rating_count: number;
  genre: string;
  image_url: string;
}

export interface ReviewResponse {
  status: string;
  copyright: string;
  num_results: number;
  results: Reviews[];
}

export interface Reviews {
  url: string;
  publication_dt: string;
  byline: string;
  book_title: string;
  book_author: string;
  summary: string;
  isbn13: string[];
}
