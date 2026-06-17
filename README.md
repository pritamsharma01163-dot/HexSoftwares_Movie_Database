# 🎬 Movie Database System

A console-based Movie Database application built with Java and JDBC, connected to a MySQL database. Features user authentication, movie reviews, watchlist management, and more.

## 📌 Features
- 🔐 User Authentication (Register/Login)
- ➕ Add new movies with duplicate check
- 📋 View all movies
- 🔍 Search by Title, Genre, Year, Director
- ✏️ Update movie details
- 🗑️ Delete movies
- 📝 Movie Reviews & Star Ratings
- 📌 Watchlist Management
- 🏆 Top 5 Rated Movies
- ⭐ Rating Filter (7+, 8+, 9+)
- 🕐 Most Recent Movies
- 📊 Genre Statistics
- 📁 Export to CSV
- 🔢 Total Movie Count

## 🛠️ Technologies Used
- Java
- JDBC
- MySQL 8.0

## 🗄️ Database Setup
1. Install MySQL
2. Open MySQL Command Line
3. Run these commands:

```sql
CREATE DATABASE movie_db;
USE movie_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    director VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    rating DOUBLE NOT NULL,
    description VARCHAR(500)
);

CREATE TABLE watchlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    added_date DATE NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    review_text VARCHAR(500) NOT NULL,
    stars INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);
```

## ▶️ How to Run

### Option 1 — Easy Way (Recommended):
.\run.bat
### Option 2 — Manual:
**Compile:**
javac -cp "lib\mysql-connector-j-9.7.0.jar" -d out src\dao\DBConnection.java src\model\Movie.java src\dao\MovieDAO.java src\dao\WatchlistDAO.java src\dao\UserDAO.java src\dao\ReviewDAO.java src\ui\Menu.java src\Main.java
**Run:**
java -cp "out;lib\mysql-connector-j-9.7.0.jar" Main
## 📁 Project Structure
MovieDatabase/

├── src/

│   ├── Main.java

│   ├── dao/

│   │   ├── DBConnection.java

│   │   ├── MovieDAO.java

│   │   ├── WatchlistDAO.java

│   │   ├── UserDAO.java

│   │   └── ReviewDAO.java

│   ├── model/

│   │   └── Movie.java

│   └── ui/

│       └── Menu.java

├── lib/

│   └── mysql-connector-j-9.7.0.jar

├── run.bat

└── README.md
## 📊 Menu Options
1.Add Movie
2.View All Movies
3.Search Movie (Title/Genre/Year/Director)
4.Update Movie
5.Delete Movie
6.Watchlist
7.Top Rated Movies
8.Export to CSV
9.Movie Count
10.Rating Filter
11.Most Recent Movies
12.Genre Statistics
13.Movie Reviews
14.Logout
## 👨‍💻 Author
Pritam Sharma
