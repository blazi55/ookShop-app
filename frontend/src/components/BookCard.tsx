import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import { BookItem } from '../data/fallbackBooks';
import { useCart } from '../context/CartContext';

interface BookCardProps {
  book: BookItem;
}

function formatPrice(value: number, language: string) {
  return new Intl.NumberFormat(language === 'en' ? 'en-US' : 'pl-PL', {
    style: 'currency',
    currency: 'PLN',
  }).format(value);
}

export function BookCard({ book }: BookCardProps) {
  const { t, i18n } = useTranslation();
  const { addItem } = useCart();
  const [quantity, setQuantity] = useState(1);
  const [justAdded, setJustAdded] = useState(false);

  const handleAdd = () => {
    addItem(book, quantity);
    setJustAdded(true);
    window.setTimeout(() => setJustAdded(false), 1200);
  };

  return (
    <article className={`book-card tone-${book.coverTone || 'ink'}`}>
      <div className="book-card__cover" aria-hidden="true">
        <span className="book-card__spine" />
        <span className="book-card__title-ghost">{book.name}</span>
      </div>
      <div className="book-card__body">
        <h3>{book.name}</h3>
        <p className="book-card__price">{formatPrice(book.price, i18n.language)}</p>
        <div className="book-card__qty">
          <label htmlFor={`qty-${book.id}`}>{t('catalog.quantity')}</label>
          <div className="qty-control">
            <button
              type="button"
              aria-label="-"
              onClick={() => setQuantity((q) => Math.max(1, q - 1))}
            >
              −
            </button>
            <input
              id={`qty-${book.id}`}
              type="number"
              min={1}
              value={quantity}
              onChange={(e) => setQuantity(Math.max(1, Number(e.target.value) || 1))}
            />
            <button type="button" aria-label="+" onClick={() => setQuantity((q) => q + 1)}>
              +
            </button>
          </div>
        </div>
        <button type="button" className="btn-primary" onClick={handleAdd}>
          {justAdded ? t('catalog.added') : t('catalog.addToCart')}
        </button>
      </div>
    </article>
  );
}
