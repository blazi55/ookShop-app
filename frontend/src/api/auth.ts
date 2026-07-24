import { apiFetch } from './client';

export interface AuthUser {
  id: number;
  fullName: string;
  email: string;
  login: string;
  token: string;
}

export async function loginRequest(login: string, password: string): Promise<AuthUser> {
  return apiFetch<AuthUser>('/userOokShop/login', {
    method: 'POST',
    body: JSON.stringify({ login, password }),
  });
}

export async function registerRequest(payload: {
  fullName: string;
  email: string;
  login: string;
  password: string;
}): Promise<AuthUser> {
  return apiFetch<AuthUser>('/userOokShop/register', {
    method: 'POST',
    body: JSON.stringify(payload),
  });
}
