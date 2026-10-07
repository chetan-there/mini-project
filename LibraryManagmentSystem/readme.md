# Library Management System

A complete **Java-based web application** designed for beginners to understand real-world development using the **MVC (Model-View-Controller)** architecture. This system allows for the management of library resources, including book tracking, member registration, and issue/return workflows. The application features an **Admin-only login** for secure management.

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Tech Stack](#-tech-stack)
- [Key Modules](#-key-modules)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
  - [1. Clone the Repository](#1-clone-the-repository)
  - [2. Set Up the Database](#2-set-up-the-database)
  - [3. Configure the Project](#3-configure-the-project)
  - [4. Deploy and Run](#4-deploy-and-run)
- [Project Structure](#-project-structure)
- [Screenshots](#-screenshots)
- [Resources](#-resources)
- [Contributing](#-contributing)
- [License](#-license)
- [Author](#-author)

---

## 📖 Project Overview

The **Library Management System** is a web-based application built using Java EE technologies. It provides a simple yet functional interface for librarians/admins to manage books, members, and the issue/return process. This project is ideal for beginners who want to understand how JSP, Servlets, and MySQL work together in a real-world MVC application.

---

## 🛠 Tech Stack

| Layer | Technology |
|-------|------------|
| **Language** | Java (Core) |
| **Web Technologies** | JSP, Servlet, HTML, CSS, JavaScript |
| **Database** | MySQL |
| **Server** | Apache Tomcat |
| **Development Tools** | Java 1.8, Spring Tool Suite (STS) / Eclipse, MySQL Workbench |

---

## 🔑 Key Modules

| # | Module | Description | Timestamp |
|---|--------|-------------|-----------|
| 1 | **Dashboard** | Displays counts of books and active issue status. | `0:38` |
| 2 | **Book Management** | Create, Read, Update, and Delete (CRUD) operations for book entries. | `1:03` |
| 3 | **User/Member Management** | Register and track members for book assignments. | `1:10` |
| 4 | **Issue/Return Workflow** | Assign books to members with due dates and return tracking. | `1:35` |
| 5 | **Authentication** | Secure admin login flow for system access. | `2:06` |

---

## ✅ Prerequisites

To successfully build and run this project, you should have a working knowledge of:

- **Java**: Loops, conditionals, and Object-Oriented Programming (OOP) concepts.
- **Database**: SQL basics (queries, tables, and relationships).
- **Web**: Basic understanding of HTML/CSS for template adjustments.

### Software Requirements

- **JDK 1.8** or higher
- **Apache Tomcat 8.5** or higher
- **MySQL 5.7** or higher
- **Eclipse IDE** or **Spring Tool Suite (STS)**
- **MySQL Workbench** (optional, for database management)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/chetan-there/mini-project.git
cd mini-project/LibraryManagmentSystem
```

### 2. Set Up the Database

1. Open **MySQL Workbench** (or any MySQL client).
2. Create a new database:

```sql
CREATE DATABASE library_db;
USE library_db;
```

3. Import the SQL script (if provided) or create the required tables manually:

```sql
-- Books table
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    isbn VARCHAR(50),
    quantity INT DEFAULT 1
);

-- Members table
CREATE TABLE members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(15)
);

-- Issue records table
CREATE TABLE issue_records (
    id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    member_id INT,
    issue_date DATE,
    due_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);

-- Admin table
CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255)
);

-- Insert default admin (change password before production!)
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');
```

### 3. Configure the Project

1. Open the project in **Eclipse** or **STS**.
2. Navigate to the database configuration file (e.g., `DBConnection.java` or `db.properties`).
3. Update the database credentials:

```java
String url = "jdbc:mysql://localhost:3306/library_db";
String username = "root";
String password = "your_mysql_password";
```

4. Ensure the **MySQL Connector/J** (JDBC driver) is added to the project's `WEB-INF/lib` folder.

### 4. Deploy and Run

1. Right-click the project → **Run As** → **Run on Server**.
2. Select **Apache Tomcat** and click **Finish**.
3. Open your browser and navigate to:

```
http://localhost:8080/LibraryManagmentSystem/
```

4. Log in with the default admin credentials (or the ones you created).

---

## 📁 Project Structure

```
LibraryManagmentSystem/
│
├── src/
│   └── com/
│       └── library/
│           ├── controller/      # Servlets (Controllers)
│           ├── dao/             # Data Access Objects
│           ├── model/           # Java Beans (Models)
│           └── util/            # Utility classes (DB connection)
│
├── WebContent/
│   ├── WEB-INF/
│   │   ├── web.xml              # Deployment descriptor
│   │   └── lib/                 # JAR files (MySQL connector)
│   │
│   ├── css/                     # Stylesheets
│   ├── js/                      # JavaScript files
│   ├── images/                  # Static assets
│   ├── index.jsp                # Login page
│   ├── dashboard.jsp            # Admin dashboard
│   ├── books.jsp                # Book management
│   ├── members.jsp              # Member management
│   └── issue.jsp                # Issue/Return workflow
│
└── README.md
```

---

## 🖼 Screenshots

> Add screenshots of your application here to give users a visual preview.

| Page | Preview |
|------|---------|
| Login | `(add screenshot)` |
| Dashboard | `(add screenshot)` |
| Book Management | `(add screenshot)` |
| Member Management | `(add screenshot)` |
| Issue/Return | `(add screenshot)` |

---

## 📚 Resources

- **Project Assets**: Links for UI templates and tutorials are provided in the video description.
- **Video Tutorial**: [Watch the full walkthrough](https://github.com/chetan-there/mini-project) *(replace with actual video link)*

---

## 🤝 Contributing

Contributions are welcome! If you'd like to improve this project:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request.

---

## 📄 License

This project is open-source and available for learning purposes. Feel free to use, modify, and distribute it as needed.

---

## 👨‍💻 Author

**Chetan There**

- GitHub: [@chetan-there](https://github.com/chetan-there)

---

⭐ If you found this project helpful, please give it a star on GitHub!
