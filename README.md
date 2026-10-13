# Mini ERP System — Backend

A full-stack Enterprise Resource Planning (ERP) application designed to manage employees, inventory, purchasing, sales, invoices, payments, and business operations through a centralized system.

The backend is built with Java and Spring Boot, using PostgreSQL for persistent data storage and JWT-based authentication to secure API endpoints.

## Live Demo

- **Backend API:** https://erp-i5vi.onrender.com
- **Frontend Application:** https://erp-frontend.tanzimm651.workers.dev
- **Frontend Repository:** Add your frontend GitHub repository URL here.

> Note: The backend is an API service, so opening the root URL in a browser may not display a user interface.

## Features

- **Authentication and Authorization** — JWT-based authentication and role-based access control.
- **Employee Management** — Manage employee records and department assignments.
- **Department Management** — Organize employees by department.
- **Product Management** — Maintain product information.
- **Warehouse Management** — Manage warehouse details and locations.
- **Inventory Management** — Track inventory and stock movements.
- **Supplier Management** — Maintain supplier information.
- **Purchase Orders** — Create purchase orders and track receiving status.
- **Customer Management** — Manage customer records.
- **Sales Orders** — Record and manage sales transactions.
- **Customer Invoices** — Generate and track customer invoices.
- **Payment Management** — Record payments and track outstanding balances.
- **Dashboard** — View business summaries and financial metrics.
- **Audit Logging** — Record selected business operations.
- **Validation and Exception Handling** — Handle invalid requests and application errors.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 25 | Backend programming language |
| Spring Boot | Application framework |
| Spring Security | API security |
| JWT | Token-based authentication |
| Spring Data JPA / Hibernate | Database persistence |
| PostgreSQL | Relational database |
| Maven | Dependency management and build |
| Postman | API testing |
| Render | Backend deployment |
| Neon PostgreSQL | Hosted database |

## Architecture

The application follows a layered backend architecture:

- **Controller Layer:** Receives HTTP requests and returns API responses.
- **Service Layer:** Implements business logic.
- **Repository Layer:** Communicates with the database.
- **Entity Layer:** Represents persistent database entities.
- **DTO Layer:** Defines request and response data structures.
- **Security Layer:** Handles authentication and authorization.
- **Audit and Exception Handling:** Supports operation tracking and error responses.

## Local Setup

### Prerequisites

- Java Development Kit (JDK) 25
- PostgreSQL
- Git
- Maven, or the included Maven Wrapper

### 1. Clone the repository

```bash
git clone https://github.com/MahirTanzim/erp.git
cd erp
```

### 2. Create a PostgreSQL database

Create a database named `erp` in your local PostgreSQL installation.

### 3. Configure environment variables

Configure the following environment variables in your local development environment:

```text
DB_URL=jdbc:postgresql://localhost:5432/erp
DB_USERNAME=your_postgres_username
DB_PASSWORD=your_postgres_password
JWT_SECRET=your_long_random_secret
```

Use your own local credentials and a strong, randomly generated JWT secret. Never commit real credentials or production secrets to GitHub.

The application reads these variables through `application.properties`.

### 4. Run the application

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will normally be available at:

```text
http://localhost:8080
```

### 5. Test the API

Use Postman or another HTTP client to test the API endpoints. Authentication is required for protected endpoints.

Example login endpoint:

```http
POST /api/auth/login
```

Consult the controller classes for the exact request formats and available endpoints.

## Deployment

The backend is deployed on Render and connects to a hosted PostgreSQL database on Neon.

Production configuration is supplied through environment variables rather than hardcoded credentials.

## Security Notes

- Protected endpoints require authentication.
- Access restrictions are applied to selected operations based on user roles.
- Passwords are hashed using BCrypt.
- JWT signing secrets and database credentials should be stored outside source code.
- Production credentials must never be committed to version control.

## Future Improvements

- Expand automated unit and integration testing.
- Improve API documentation with OpenAPI/Swagger.
- Add pagination and filtering for large datasets.
- Improve query performance and database indexing.
- Add more comprehensive role and permission tests.
- Add automated deployment checks.

## Author

**Mahir Tanzim**

Computer Science graduate and software developer.

## License

No license has been specified yet. All rights are reserved unless a license is added to this repository.