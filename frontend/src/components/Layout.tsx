import React, { ReactNode } from 'react';
import { useTranslation } from 'react-i18next';
import { Header } from './Header';

export function Layout({ children }: { children: ReactNode }) {
  const { t } = useTranslation();

  return (
    <div className="app-shell">
      <Header />
      <main className="app-main">{children}</main>
      <footer className="site-footer">
        <p>{t('footer.tagline')}</p>
      </footer>
    </div>
  );
}
