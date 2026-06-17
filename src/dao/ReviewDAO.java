package dao;

import java.sql.*;

public class ReviewDAO {

    // ADD REVIEW
    public void addReview(int movieId, String username, 
                         String reviewText, int stars) {
        String query = "INSERT INTO reviews (movie_id, username, review_text, stars) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, movieId);
            stmt.setString(2, username);
            stmt.setString(3, reviewText);
            stmt.setInt(4, stars);
            stmt.executeUpdate();
            System.out.println("\n Review added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // VIEW REVIEWS OF A MOVIE
    public void getReviews(int movieId) {
        String query = "SELECT * FROM reviews WHERE movie_id=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n========= Movie Reviews =========");
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("\nReviewer : " + rs.getString("username"));
                System.out.println("Stars    : " + rs.getInt("stars") + "/5");
                System.out.println("Review   : " + rs.getString("review_text"));
                System.out.println("----------------------------");
            }
            if (count == 0) {
                System.out.println("No reviews yet for this movie!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}