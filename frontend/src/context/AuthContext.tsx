import React, {
  createContext,
  useContext,
  useMemo,
  useState,
  ReactNode,
} from 'react';
import { AuthUser, loginRequest, registerRequest } from '../api/auth';

interface AuthContextValue {
  user: AuthUser | null;
  login: (login: string, password: string) => Promise<void>;
  register: (payload: {
    fullName: string;
    email: string;
    login: string;
    password: string;
  }) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);
const STORAGE_KEY = 'ookshop-user';

function loadUser(): AuthUser | null {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return null;
    return JSON.parse(raw) as AuthUser;
  } catch {
    return null;
  }
}

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<AuthUser | null>(() => loadUser());

  const value = useMemo<AuthContextValue>(() => {
    const persist = (next: AuthUser | null) => {
      setUser(next);
      if (next) {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
      } else {
        localStorage.removeItem(STORAGE_KEY);
      }
    };

    return {
      user,
      login: async (loginName, password) => {
        const next = await loginRequest(loginName, password);
        persist(next);
      },
      register: async (payload) => {
        const next = await registerRequest(payload);
        persist(next);
      },
      logout: () => persist(null),
    };
  }, [user]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return ctx;
}
