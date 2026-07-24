import React from 'react';
import { useTranslation } from 'react-i18next';

export function LanguageSwitcher() {
  const { i18n, t } = useTranslation();

  return (
    <div className="lang-switch" role="group" aria-label="Language">
      <button
        type="button"
        className={i18n.language?.startsWith('pl') ? 'is-active' : ''}
        onClick={() => i18n.changeLanguage('pl')}
      >
        {t('lang.pl')}
      </button>
      <button
        type="button"
        className={i18n.language?.startsWith('en') ? 'is-active' : ''}
        onClick={() => i18n.changeLanguage('en')}
      >
        {t('lang.en')}
      </button>
    </div>
  );
}
