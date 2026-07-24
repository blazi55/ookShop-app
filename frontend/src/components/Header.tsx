import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import { LanguageSwitcher } from './LanguageSwitcher';

export function Header() {
  const { t } = useTranslation();
  const { itemCount } = useCart();
  const { user, logout } = useAuth();

  return (
    <header className="site-header">
      <div className="site-header__inner">
        <Link to="/" className="brand" aria-label={t('brand')}>
          <span className="brand__mark">ook</span>
          <span className="brand__name">Shop</span>
        </Link>

        <nav className="site-nav" aria-label="Main">
          <NavLink to="/" end>
            {t('nav.catalog')}
          </NavLink>
          <NavLink to="/cart" className="cart-link">
            {t('nav.cart')}
            {itemCount > 0 && <span className="cart-badge">{itemCount}</span>}
          </NavLink>
          {user ? (
            <>
              <span className="nav-user">{t('auth.welcome', { name: user.fullName || user.login })}</span>
              <button type="button" className="linkish" onClick={logout}>
                {t('nav.logout')}
              </button>
            </>
          ) : (
            <>
              <NavLink to="/login">{t('nav.login')}</NavLink>
              <NavLink to="/register" className="nav-cta">
                {t('nav.register')}
              </NavLink>
            </>
          )}
          <LanguageSwitcher />
        </nav>
      </div>
    </header>
  );
}
