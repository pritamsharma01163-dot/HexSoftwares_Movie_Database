package dao;

import java.sql.*;

public class UserDAO {

    // REGISTER
    public boolean register(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("\n Registration successful!");
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("\n Username already exists! Try another.");
            } else {
                System.out.println("Error: " + e.getMessage());
            }
            return false;
        }
    }

    // LOGIN
    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("\n Login successful! Welcome " + username + "!");
                return true;
            } else {
                System.out.println("\n Invalid username or password!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}