export interface BookItem {
  id: number;
  name: string;
  price: number;
  coverTone?: string;
}

export const FALLBACK_BOOKS: BookItem[] = [
  { id: 1, name: 'The Silent Library', price: 39.9, coverTone: 'ink' },
  { id: 2, name: 'Midnight Manuscript', price: 44.5, coverTone: 'forest' },
  { id: 3, name: 'Pages of Autumn', price: 32.0, coverTone: 'amber' },
  { id: 4, name: 'Ink & Ember', price: 48.9, coverTone: 'ember' },
  { id: 5, name: "The Cartographer's Tale", price: 54.0, coverTone: 'sea' },
  { id: 6, name: 'Harbor of Stories', price: 36.5, coverTone: 'slate' },
];

const COVER_TONES = ['ink', 'forest', 'amber', 'ember', 'sea', 'slate'];

export function withCoverTone(book: BookItem, index: number): BookItem {
  return {
    ...book,
    coverTone: book.coverTone || COVER_TONES[index % COVER_TONES.length],
  };
}
