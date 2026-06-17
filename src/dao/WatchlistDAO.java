package dao;

import java.sql.*;
import java.time.LocalDate;

public class WatchlistDAO {

    // 1. ADD TO WATCHLIST
    public void addToWatchlist(int movieId, String status) {
        String query = "INSERT INTO watchlist (movie_id, status, added_date) VALUES (?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, movieId);
            stmt.setString(2, status);
            stmt.setString(3, LocalDate.now().toString());
            stmt.executeUpdate();
            System.out.println("\n Movie added to watchlist successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 2. VIEW ALL WATCHLIST
    public void getAllWatchlist() {
        String query = "SELECT w.id, m.title, m.genre, w.status, w.added_date " +
                      "FROM watchlist w " +
                      "JOIN movies m ON w.movie_id = m.id";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n========= My Watchlist =========");
            while (rs.next()) {
                System.out.println("\nID       : " + rs.getInt("id"));
                System.out.println("Title    : " + rs.getString("title"));
                System.out.println("Genre    : " + rs.getString("genre"));
                System.out.println("Status   : " + rs.getString("status"));
                System.out.println("Added    : " + rs.getString("added_date"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 3. UPDATE STATUS
    public void updateStatus(int id, String status) {
        String query = "UPDATE watchlist SET status=? WHERE id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("\n Status updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 4. REMOVE FROM WATCHLIST
    public void removeFromWatchlist(int id) {
        String query = "DELETE FROM watchlist WHERE id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("\n Movie removed from watchlist successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}