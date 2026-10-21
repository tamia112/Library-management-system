# Library Management System

A desktop application for managing a library. It handles books, students, issuing and returning books, overdue fines, and basic statistics, with all data stored in a MySQL database.

Built with **Java Swing** and **MySQL**.

---

## Features

- **Login and accounts**: secure sign-in and account creation with user roles
- **Book management**: add, update, delete and search books
- **Student management**: register students and view the student list
- **Issue books**: issue a book to a student with issue and return dates
- **Return books**: process returns and update available copies automatically
- **Issued books**: see every book that is currently out
- **Available books**: see which books can still be borrowed
- **Fines**: overdue days and fines are calculated on return
- **Overdue notifications**: spot late returns quickly
- **Statistics**: an overview of library activity

## Tech stack

| Part | Technology |
|------|------------|
| Language | Java (Swing GUI) |
| Database | MySQL / MariaDB |
| Database tool | XAMPP + phpMyAdmin |
| Driver | MySQL Connector/J 8.0.17 |
| GUI designer | NetBeans (project structure) |

## Project structure

```
Library-management-system/
├── src/            Java source files and images
│   └── org/netbeans/lib/awtextra/   Layout helper classes
├── lib/            MySQL Connector/J jar
├── lms.sql         Database schema and sample data
├── build.xml       Ant build file (NetBeans)
└── README.md
```

## Requirements

- JDK 17 or newer
- XAMPP (or any MySQL / MariaDB server)
- `mysql-connector-java-8.0.17.jar` in the `lib` folder

## Setup

### 1. Start MySQL
Start XAMPP and turn on **MySQL** (and **Apache** if you want to use phpMyAdmin).

On Linux:
```bash
sudo /opt/lampp/lampp start
```

### 2. Import the database
1. Open `http://localhost/phpmyadmin`
2. Click **Import**, choose `lms.sql`, and click **Go**

This creates the `lms` database with all tables and sample data.

### 3. Compile and run

**Linux / macOS**
```bash
mkdir out
javac -d out src/org/netbeans/lib/awtextra/*.java
javac -cp "out:lib/*" -d out src/*.java
cp -r src/* out/
java -cp "out:lib/*" Login
```

**Windows (Command Prompt)**
```bat
mkdir out
javac -d out src\org\netbeans\lib\awtextra\*.java
javac -cp "out;lib\*" -d out src\*.java
xcopy src out /E /I /Y
java -cp "out;lib\*" Login
```

### Database connection
The app connects with these defaults (the XAMPP defaults). Change them in the source files if your setup is different.

| Setting | Value |
|---------|-------|
| Host | `localhost:3306` |
| Database | `lms` |
| User | `root` |
| Password | *(empty)* |

## Demo login

| Username | Password |
|----------|----------|
| `admin` | `admin123` |

## Database tables

| Table | Purpose |
|-------|---------|
| `user` | Login accounts and roles |
| `student` | Registered students |
| `books` | Book catalogue and copy counts |
| `bookissue` | Books currently issued |
| `newbook` | Returned books with overdue days and fines |

## Screenshots

> Add your screenshots here, for example:
>
> `![Login](screenshots/login.png)`
> `![Dashboard](screenshots/dashboard.png)`

## Possible improvements

- Hash passwords instead of storing them as plain text
- Use prepared statements everywhere to prevent SQL injection
- Move the database settings into one configuration file
- Add search and filtering across all screens
- Package the app as a runnable `.jar` release

## Author

**Tamia**
GitHub: [@tamia112](https://github.com/tamia112)

## License

This project is for learning and portfolio purposes.
