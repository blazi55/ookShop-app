import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import pl from './locales/pl.json';
import en from './locales/en.json';

const savedLanguage = localStorage.getItem('ookshop-lang') || 'pl';

i18n.use(initReactI18next).init({
  resources: {
    pl: { translation: pl },
    en: { translation: en },
  },
  lng: savedLanguage,
  fallbackLng: 'pl',
  interpolation: {
    escapeValue: false,
  },
});

i18n.on('languageChanged', (lng) => {
  localStorage.setItem('ookshop-lang', lng);
  document.documentElement.lang = lng;
});

document.documentElement.lang = savedLanguage;

export default i18n;
