import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';

function formatPrice(value: number, language: string) {
  return new Intl.NumberFormat(language === 'en' ? 'en-US' : 'pl-PL', {
    style: 'currency',
    currency: 'PLN',
  }).format(value);
}

export function CartPage() {
  const { t, i18n } = useTranslation();
  const { items, total, updateQuantity, removeItem, clearCart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [message, setMessage] = useState<string | null>(null);

  const handleCheckout = () => {
    if (!user) {
      setMessage(t('cart.loginRequired'));
      navigate('/login');
      return;
    }
    clearCart();
    setMessage(t('cart.checkoutSuccess'));
  };

  if (items.length === 0) {
    return (
      <div className="page cart-page">
        <h1>{t('cart.heading')}</h1>
        {message && <p className="success">{message}</p>}
        <div className="empty-state">
          <p>{t('cart.empty')}</p>
          <p className="muted">{t('cart.emptyHint')}</p>
          <Link to="/" className="btn-primary">
            {t('cart.browse')}
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="page cart-page">
      <h1>{t('cart.heading')}</h1>
      {message && <p className="success">{message}</p>}

      <div className="cart-table">
        {items.map((line) => (
          <div className="cart-row" key={line.book.id}>
            <div className="cart-row__info">
              <span className={`swatch tone-${line.book.coverTone || 'ink'}`} aria-hidden="true" />
              <div>
                <p className="cart-row__title">{line.book.name}</p>
                <p className="muted">{formatPrice(line.book.price, i18n.language)}</p>
              </div>
            </div>
            <div className="qty-control">
              <button
                type="button"
                onClick={() => updateQuantity(line.book.id, line.quantity - 1)}
              >
                −
              </button>
              <input
                type="number"
                min={1}
                value={line.quantity}
                onChange={(e) =>
                  updateQuantity(line.book.id, Math.max(1, Number(e.target.value) || 1))
                }
              />
              <button
                type="button"
                onClick={() => updateQuantity(line.book.id, line.quantity + 1)}
              >
                +
              </button>
            </div>
            <p className="cart-row__line-total">
              {formatPrice(line.book.price * line.quantity, i18n.language)}
            </p>
            <button
              type="button"
              className="linkish"
              onClick={() => removeItem(line.book.id)}
            >
              {t('cart.remove')}
            </button>
          </div>
        ))}
      </div>

      <div className="cart-summary">
        <p>
          {t('cart.total')}: <strong>{formatPrice(total, i18n.language)}</strong>
        </p>
        <button type="button" className="btn-primary" onClick={handleCheckout}>
          {t('cart.checkout')}
        </button>
      </div>
    </div>
  );
}
