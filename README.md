# ookShop

Księgarnia online — Spring Boot + React.

## Stack

- **Backend:** Java 17, Spring Boot 3, Spring Security (JWT), JPA, MySQL
- **Frontend:** React 18, TypeScript, React Router, i18next (PL / EN)

## Uruchomienie

### 1. MySQL

Utwórz bazę:

```sql
CREATE DATABASE ookshop;
```

Domyślne dane w `backend/src/main/resources/application.properties`:
- URL: `jdbc:mysql://localhost:3306/ookshop`
- user / hasło: `root` / `root`

### 2. Backend

```bash
cd backend
./mvnw spring-boot:run
```

API: `http://localhost:8080`

Publiczne endpointy:
- `GET /bookOokShop/books`
- `POST /userOokShop/register`
- `POST /userOokShop/login`

Przy starcie seeduje przykładowe książki.

### 3. Frontend

```bash
cd frontend
npm install
npm start
```

Aplikacja: `http://localhost:3000`

Opcjonalnie: `REACT_APP_API_URL=http://localhost:8080`

Jeśli backend jest niedostępny, katalog używa lokalnych danych zapasowych.

## Funkcje frontendu

- Katalog książek (API lub fallback)
- Koszyk (localStorage)
- Logowanie / rejestracja
- Przełącznik języka **PL / EN**
- Responsywny, branded UI

## Struktura

```
ookShop/
├── backend/     # Spring Boot API
└── frontend/    # React SPA
```
