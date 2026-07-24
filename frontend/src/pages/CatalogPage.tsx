import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { fetchBooks } from '../api/books';
import { BookItem } from '../data/fallbackBooks';
import { BookCard } from '../components/BookCard';

export function CatalogPage() {
  const { t } = useTranslation();
  const [books, setBooks] = useState<BookItem[]>([]);
  const [loading, setLoading] = useState(true);
  const [fromApi, setFromApi] = useState(true);

  useEffect(() => {
    let cancelled = false;
    (async () => {
      setLoading(true);
      const result = await fetchBooks();
      if (!cancelled) {
        setBooks(result.books);
        setFromApi(result.fromApi);
        setLoading(false);
      }
    })();
    return () => {
      cancelled = true;
    };
  }, []);

  return (
    <div className="page catalog-page">
      <section className="hero">
        <div className="hero__copy">
          <p className="hero__brand">{t('brand')}</p>
          <h1>{t('hero.title')}</h1>
          <p className="hero__subtitle">{t('hero.subtitle')}</p>
        </div>
        <div className="hero__visual" aria-hidden="true">
          <div className="hero__shelf">
            <span />
            <span />
            <span />
            <span />
          </div>
        </div>
      </section>

      <section className="catalog-section">
        <div className="section-head">
          <h2>{t('catalog.heading')}</h2>
          {!fromApi && !loading && <p className="notice">{t('catalog.error')}</p>}
        </div>

        {loading && <p className="muted">{t('catalog.loading')}</p>}

        {!loading && books.length === 0 && <p className="muted">{t('catalog.empty')}</p>}

        {!loading && books.length > 0 && (
          <div className="book-grid">
            {books.map((book) => (
              <BookCard key={book.id} book={book} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
}
