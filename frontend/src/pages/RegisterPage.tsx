import React, { FormEvent, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import { useAuth } from '../context/AuthContext';

export function RegisterPage() {
  const { t } = useTranslation();
  const { register } = useAuth();
  const navigate = useNavigate();
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [loginName, setLoginName] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  const onSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError(null);
    setSubmitting(true);
    try {
      await register({ fullName, email, login: loginName, password });
      navigate('/');
    } catch {
      setError(t('auth.registerError'));
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="page auth-page">
      <form className="auth-form" onSubmit={onSubmit}>
        <h1>{t('auth.registerTitle')}</h1>
        {error && <p className="error">{error}</p>}
        <label>
          {t('auth.fullName')}
          <input value={fullName} onChange={(e) => setFullName(e.target.value)} required />
        </label>
        <label>
          {t('auth.email')}
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            autoComplete="email"
          />
        </label>
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
            autoComplete="new-password"
            minLength={4}
          />
        </label>
        <button type="submit" className="btn-primary" disabled={submitting}>
          {t('auth.submitRegister')}
        </button>
        <p className="auth-switch">
          {t('auth.hasAccount')} <Link to="/login">{t('nav.login')}</Link>
        </p>
      </form>
    </div>
  );
}
