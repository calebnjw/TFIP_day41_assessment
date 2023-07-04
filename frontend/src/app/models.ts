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
