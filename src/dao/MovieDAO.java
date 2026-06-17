package dao;

import model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // 1. ADD MOVIE
    public void addMovie(Movie movie) {
        String query = "INSERT INTO movies (title, genre, director, year, rating, description) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getYear());
            stmt.setDouble(5, movie.getRating());
            stmt.setString(6, movie.getDescription());
            stmt.executeUpdate();
            System.out.println("\n Movie added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 2. VIEW ALL MOVIES
    public void getAllMovies() {
        String query = "SELECT * FROM movies";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n========= All Movies =========");
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getInt("year"),
                    rs.getDouble("rating"),
                    rs.getString("description")
                );
                System.out.println(movie);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
   // SEARCH BY TITLE
public void searchByTitle(String title) {
    String query = "SELECT * FROM movies WHERE title LIKE ?";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + title + "%");
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n===== Search Results =====");
        int count = 0;
        while (rs.next()) {
            count++;
            Movie movie = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("description")
            );
            System.out.println(movie);
        }
        if (count == 0) {
            System.out.println("No movies found!");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// SEARCH BY GENRE
public void searchByGenre(String genre) {
    String query = "SELECT * FROM movies WHERE genre LIKE ?";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + genre + "%");
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n===== Genre Results =====");
        int count = 0;
        while (rs.next()) {
            count++;
            Movie movie = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("description")
            );
            System.out.println(movie);
        }
        if (count == 0) {
            System.out.println("No movies found!");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// SEARCH BY YEAR
public void searchByYear(int year) {
    String query = "SELECT * FROM movies WHERE year = ?";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, year);
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n===== Year Results =====");
        int count = 0;
        while (rs.next()) {
            count++;
            Movie movie = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("description")
            );
            System.out.println(movie);
        }
        if (count == 0) {
            System.out.println("No movies found!");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// SEARCH BY DIRECTOR
public void searchByDirector(String director) {
    String query = "SELECT * FROM movies WHERE director LIKE ?";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + director + "%");
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n===== Director Results =====");
        int count = 0;
        while (rs.next()) {
            count++;
            Movie movie = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("description")
            );
            System.out.println(movie);
        }
        if (count == 0) {
            System.out.println("No movies found!");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
           
    // 6. UPDATE MOVIE
    public void updateMovie(int id, String title, String genre,
                           String director, int year,
                           double rating, String description) {
        String query = "UPDATE movies SET title=?, genre=?, director=?, year=?, rating=?, description=? WHERE id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setString(3, director);
            stmt.setInt(4, year);
            stmt.setDouble(5, rating);
            stmt.setString(6, description);
            stmt.setInt(7, id);
            stmt.executeUpdate();
            System.out.println("\n Movie updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 7. DELETE MOVIE
    public void deleteMovie(int id) {
        String query = "DELETE FROM movies WHERE id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("\n Movie deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // 8. TOP RATED MOVIES
public void getTopRatedMovies() {
    String query = "SELECT * FROM movies ORDER BY rating DESC LIMIT 5";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n========= Top 5 Rated Movies =========");
        while (rs.next()) {
            Movie movie = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("description")
            );
            System.out.println(movie);
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// 9. SEARCH BY DIRECTOR

// 10. EXPORT TO CSV
public void exportToCSV() {
    String query = "SELECT * FROM movies";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        java.io.FileWriter fw = new java.io.FileWriter("movies.csv");
        fw.write("ID,Title,Genre,Director,Year,Rating,Description\n");

        while (rs.next()) {
            fw.write(rs.getInt("id") + "," +
                    rs.getString("title") + "," +
                    rs.getString("genre") + "," +
                    rs.getString("director") + "," +
                    rs.getInt("year") + "," +
                    rs.getDouble("rating") + "," +
                    rs.getString("description") + "\n");
        }
        fw.close();
        System.out.println("\n Movies exported to movies.csv successfully!");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
// DUPLICATE CHECK
public boolean isDuplicate(String title) {
    String query = "SELECT * FROM movies WHERE title = ?";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, title);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}

// MOVIE COUNT
public void getMovieCount() {
    String query = "SELECT COUNT(*) AS total FROM movies";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            System.out.println("\n Total Movies in Database: " + 
                             rs.getInt("total"));
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// RATING FILTER
public void getMoviesByRating(double minRating) {
    String query = "SELECT * FROM movies WHERE rating >= ? ORDER BY rating DESC";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDouble(1, minRating);
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n===== Movies with Rating " + minRating + "+ =====");
        int count = 0;
        while (rs.next()) {
            count++;
            Movie movie = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("description")
            );
            System.out.println(movie);
        }
        if (count == 0) {
            System.out.println("No movies found with this rating!");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// MOST RECENT MOVIES
public void getMostRecentMovies() {
    String query = "SELECT * FROM movies ORDER BY year DESC";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n========= Most Recent Movies =========");
        while (rs.next()) {
            Movie movie = new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("director"),
                rs.getInt("year"),
                rs.getDouble("rating"),
                rs.getString("description")
            );
            System.out.println(movie);
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// GENRE STATISTICS
public void getGenreStatistics() {
    String query = "SELECT genre, COUNT(*) AS total FROM movies GROUP BY genre";
    try {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        System.out.println("\n========= Genre Statistics =========");
        while (rs.next()) {
            System.out.println(rs.getString("genre") + 
                             " : " + rs.getInt("total") + " movies");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}