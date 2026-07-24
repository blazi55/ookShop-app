import { apiFetch } from './client';
import { BookItem, FALLBACK_BOOKS, withCoverTone } from '../data/fallbackBooks';

interface BookDto {
  id: number;
  name: string;
  price: number;
}

export async function fetchBooks(): Promise<{ books: BookItem[]; fromApi: boolean }> {
  try {
    const data = await apiFetch<BookDto[]>('/bookOokShop/books');
    const books = data.map((book, index) =>
      withCoverTone(
        {
          id: book.id,
          name: book.name,
          price: book.price,
        },
        index
      )
    );
    return { books, fromApi: true };
  } catch {
    return { books: FALLBACK_BOOKS, fromApi: false };
  }
}
