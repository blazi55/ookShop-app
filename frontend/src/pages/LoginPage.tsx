import React, { FormEvent, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useAuth } from '../context/AuthContext';

export function LoginPage() {
  const { t } = useTranslation();
  const { login } = useAuth();
  const navigate = useNavigate();
  const [loginName, setLoginName] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  const onSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError(null);
    setSubmitting(true);
    try {
      await login(loginName, password);
      navigate('/cart');
    } catch {
      setError(t('auth.error'));
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="page auth-page">
      <form className="auth-form" onSubmit={onSubmit}>
        <h1>{t('auth.loginTitle')}</h1>
        {error && <p className="error">{error}</p>}
        <label>
          {t('auth.login')}
          <input
            value={loginName}
            onChange={(e) => setLoginName(e.target.value)}
            required
            autoComplete="username"
          />
        </label>
        <label>
          {t('auth.password')}
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            autoComplete="current-password"
          />
        </label>
        <button type="submit" className="btn-primary" disabled={submitting}>
          {t('auth.submitLogin')}
        </button>
        <p className="auth-switch">
          {t('auth.noAccount')} <Link to="/register">{t('nav.register')}</Link>
        </p>
      </form>
    </div>
  );
}
