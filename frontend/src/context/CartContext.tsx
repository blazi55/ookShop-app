import React, {
  createContext,
  useContext,
  useEffect,
  useMemo,
  useState,
  ReactNode,
} from 'react';
import { BookItem } from '../data/fallbackBooks';

export interface CartLine {
  book: BookItem;
  quantity: number;
}

interface CartContextValue {
  items: CartLine[];
  itemCount: number;
  total: number;
  addItem: (book: BookItem, quantity?: number) => void;
  updateQuantity: (bookId: number, quantity: number) => void;
  removeItem: (bookId: number) => void;
  clearCart: () => void;
}

const CartContext = createContext<CartContextValue | undefined>(undefined);
const STORAGE_KEY = 'ookshop-cart';

function loadCart(): CartLine[] {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return [];
    return JSON.parse(raw) as CartLine[];
  } catch {
    return [];
  }
}

export function CartProvider({ children }: { children: ReactNode }) {
  const [items, setItems] = useState<CartLine[]>(() => loadCart());

  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(items));
  }, [items]);

  const value = useMemo<CartContextValue>(() => {
    const addItem = (book: BookItem, quantity = 1) => {
      setItems((prev) => {
        const existing = prev.find((line) => line.book.id === book.id);
        if (existing) {
          return prev.map((line) =>
            line.book.id === book.id
              ? { ...line, quantity: line.quantity + quantity }
              : line
          );
        }
        return [...prev, { book, quantity }];
      });
    };

    const updateQuantity = (bookId: number, quantity: number) => {
      setItems((prev) =>
        prev
          .map((line) => (line.book.id === bookId ? { ...line, quantity } : line))
          .filter((line) => line.quantity > 0)
      );
    };

    const removeItem = (bookId: number) => {
      setItems((prev) => prev.filter((line) => line.book.id !== bookId));
    };

    const clearCart = () => setItems([]);

    const itemCount = items.reduce((sum, line) => sum + line.quantity, 0);
    const total = items.reduce((sum, line) => sum + line.book.price * line.quantity, 0);

    return { items, itemCount, total, addItem, updateQuantity, removeItem, clearCart };
  }, [items]);

  return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
}

export function useCart() {
  const ctx = useContext(CartContext);
  if (!ctx) {
    throw new Error('useCart must be used within CartProvider');
  }
  return ctx;
}
