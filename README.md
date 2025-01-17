# Payment System

## Description

This system is designed for managing payments. A client can have one or more credit cards, each associated with an account in the payment system. The client can use the account to make payments, block the account, and replenish the account. An administrator can unblock the account.

## Architecture

The project is implemented using the following technologies:

- **Backend**:
    - Java
    - Servlets, Filters
    - JDBC
    - PostgreSQL
    - Keycloak for authentication and authorization
    - Log4j for logging
    - REST for API
    - Lombok, MapStruct
    - MVC pattern
    - GOF patterns
    - Maven for dependency management
    - Tomcat as the web server

- **Frontend**:
    - React (can also use Angular or Vue.js)

## Installation and Setup

### Backend

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/paymentsystem.git
    cd paymentsystem
    ```

2. **Set up PostgreSQL:**

    ```sql
    CREATE DATABASE paymentsystem;
    CREATE USER paymentuser WITH ENCRYPTED PASSWORD 'password';
    GRANT ALL PRIVILEGES ON DATABASE paymentsystem TO paymentuser;
    ```

3. **Configure `application.properties` (if applicable):**

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/paymentsystem
    spring.datasource.username=paymentuser
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update
    ```

4. **Build the project:**

    ```bash
    mvn clean install
    ```

5. **Run the application on Tomcat:**

   Deploy the WAR file to your Tomcat server.

### Frontend

1. **Navigate to the frontend directory and create React app:**

    ```bash
    npx create-react-app paymentsystem-frontend
    cd paymentsystem-frontend
    ```

2. **Install dependencies:**

    ```bash
    npm install keycloak-js axios
    ```

3. **Configure Keycloak in your React app:**

   Create a file `src/keycloak.js`:

    ```javascript
    import Keycloak from 'keycloak-js';

    const keycloak = new Keycloak({
      url: 'http://localhost:8080/auth',
      realm: 'PaymentSystem',
      clientId: 'paymentsystem-client'
    });

    export default keycloak;
    ```

4. **Start the React app:**

    ```bash
    npm start
    ```

## Usage

### Client Operations

- **Create a client**
- **View client details**
- **Update client information**
- **Delete a client**

### Credit Card Operations

- **Create a credit card**
- **View credit card details**
- **Update credit card information**
- **Delete a credit card**

### Account Operations

- **Create an account**
- **View account details**
- **Block/unblock an account**
- **Replenish an account**

### Payment Operations

- **Create a payment**
- **View payment details**
- **Update payment information**
- **Delete a payment**

### Admin Operations

- **Unblock accounts**

## Security

- **Keycloak** is used for authentication and authorization.
- **Role-based access control**: Different functionalities are accessible based on the user's role (user/admin).

## Logging

- **Log4j** is used for logging various events within the system.

## Contributing

1. Fork the repository.
2. Create your feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a pull request.
