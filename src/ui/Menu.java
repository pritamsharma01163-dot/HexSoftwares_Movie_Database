package ui;

import dao.MovieDAO;
import dao.WatchlistDAO;
import dao.UserDAO;
import dao.ReviewDAO;
import model.Movie;
import java.util.Scanner;

public class Menu {

    private MovieDAO movieDAO = new MovieDAO();
    private WatchlistDAO watchlistDAO = new WatchlistDAO();
    private UserDAO userDAO = new UserDAO();
    private ReviewDAO reviewDAO = new ReviewDAO();
    private Scanner scanner = new Scanner(System.in);
    private String loggedInUser = "";

    // AUTH MENU
    public void showAuthMenu() {
        while (true) {
            System.out.println("\n================================");
            System.out.println("  WELCOME TO MOVIE DATABASE");
            System.out.println("================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("================================");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    if (userDAO.login(username, password)) {
                        loggedInUser = username;
                        showMenu();
                    }
                    break;
                case 2:
                    System.out.print("Choose Username: ");
                    String newUser = scanner.nextLine();
                    System.out.print("Choose Password: ");
                    String newPass = scanner.nextLine();
                    userDAO.register(newUser, newPass);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // MAIN MENU
    public void showMenu() {
        while (true) {
            System.out.println("\n================================");
            System.out.println("   MOVIE DATABASE SYSTEM");
            System.out.println("   User: " + loggedInUser);
            System.out.println("================================");
            System.out.println("1.  Add Movie");
            System.out.println("2.  View All Movies");
            System.out.println("3.  Search Movie");
            System.out.println("4.  Update Movie");
            System.out.println("5.  Delete Movie");
            System.out.println("6.  Watchlist");
            System.out.println("7.  Top Rated Movies");
            System.out.println("8.  Export to CSV");
            System.out.println("9.  Movie Count");
            System.out.println("10. Rating Filter");
            System.out.println("11. Most Recent Movies");
            System.out.println("12. Genre Statistics");
            System.out.println("13. Movie Reviews");
            System.out.println("14. Logout");
            System.out.println("================================");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addMovie(); break;
                case 2: movieDAO.getAllMovies(); break;
                case 3: searchMenu(); break;
                case 4: updateMovie(); break;
                case 5: deleteMovie(); break;
                case 6: watchlistMenu(); break;
                case 7: movieDAO.getTopRatedMovies(); break;
                case 8: movieDAO.exportToCSV(); break;
                case 9: movieDAO.getMovieCount(); break;
                case 10: ratingFilterMenu(); break;
                case 11: movieDAO.getMostRecentMovies(); break;
                case 12: movieDAO.getGenreStatistics(); break;
                case 13: reviewMenu(); break;
                case 14:
                    System.out.println("Logged out successfully!");
                    loggedInUser = "";
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ADD MOVIE WITH DUPLICATE CHECK
    private void addMovie() {
        System.out.println("\n--- Add New Movie ---");
        System.out.print("Title: ");
        String title = scanner.nextLine();

        if (movieDAO.isDuplicate(title)) {
            System.out.println("\n Movie already exists in database!");
            return;
        }

        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Director: ");
        String director = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Rating (1-10): ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Movie movie = new Movie(0, title, genre, director,
                               year, rating, description);
        movieDAO.addMovie(movie);
    }

    // SEARCH MENU
    private void searchMenu() {
        System.out.println("\n--- Search By ---");
        System.out.println("1. Title");
        System.out.println("2. Genre");
        System.out.println("3. Year");
        System.out.println("4. Director");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Title: ");
                movieDAO.searchByTitle(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter Genre: ");
                movieDAO.searchByGenre(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter Year: ");
                movieDAO.searchByYear(scanner.nextInt());
                break;
            case 4:
                System.out.print("Enter Director: ");
                movieDAO.searchByDirector(scanner.nextLine());
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    // RATING FILTER MENU
    private void ratingFilterMenu() {
        System.out.println("\n--- Rating Filter ---");
        System.out.println("1. Rating 9+");
        System.out.println("2. Rating 8+");
        System.out.println("3. Rating 7+");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: movieDAO.getMoviesByRating(9.0); break;
            case 2: movieDAO.getMoviesByRating(8.0); break;
            case 3: movieDAO.getMoviesByRating(7.0); break;
            default: System.out.println("Invalid option!");
        }
    }

    // REVIEW MENU
    private void reviewMenu() {
        System.out.println("\n--- Movie Reviews ---");
        System.out.println("1. Add Review");
        System.out.println("2. View Reviews");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                movieDAO.getAllMovies();
                System.out.print("\nEnter Movie ID: ");
                int movieId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Your Review: ");
                String review = scanner.nextLine();
                System.out.print("Stars (1-5): ");
                int stars = scanner.nextInt();
                scanner.nextLine();
                reviewDAO.addReview(movieId, loggedInUser, review, stars);
                break;
            case 2:
                movieDAO.getAllMovies();
                System.out.print("\nEnter Movie ID: ");
                int viewId = scanner.nextInt();
                scanner.nextLine();
                reviewDAO.getReviews(viewId);
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    // UPDATE MOVIE
    private void updateMovie() {
        System.out.println("\n--- Update Movie ---");
        movieDAO.getAllMovies();
        System.out.print("\nEnter Movie ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New Title: ");
        String title = scanner.nextLine();
        System.out.print("New Genre: ");
        String genre = scanner.nextLine();
        System.out.print("New Director: ");
        String director = scanner.nextLine();
        System.out.print("New Year: ");
        int year = scanner.nextInt();
        System.out.print("New Rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("New Description: ");
        String description = scanner.nextLine();
        movieDAO.updateMovie(id, title, genre,
                            director, year, rating, description);
    }

    // DELETE MOVIE
    private void deleteMovie() {
        System.out.println("\n--- Delete Movie ---");
        movieDAO.getAllMovies();
        System.out.print("\nEnter Movie ID to delete: ");
        int id = scanner.nextInt();
        movieDAO.deleteMovie(id);
    }

    // WATCHLIST MENU
    private void watchlistMenu() {
        System.out.println("\n--- Watchlist ---");
        System.out.println("1. Add to Watchlist");
        System.out.println("2. View Watchlist");
        System.out.println("3. Update Status");
        System.out.println("4. Remove from Watchlist");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                movieDAO.getAllMovies();
                System.out.print("\nEnter Movie ID: ");
                int movieId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Select Status:");
                System.out.println("1. Want to Watch");
                System.out.println("2. Watched");
                System.out.print("Choose: ");
                int statusChoice = scanner.nextInt();
                String status = (statusChoice == 1) ?
                               "want_to_watch" : "watched";
                watchlistDAO.addToWatchlist(movieId, status);
                break;
            case 2:
                watchlistDAO.getAllWatchlist();
                break;
            case 3:
                watchlistDAO.getAllWatchlist();
                System.out.print("\nEnter Watchlist ID: ");
                int wId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Select New Status:");
                System.out.println("1. Want to Watch");
                System.out.println("2. Watched");
                System.out.print("Choose: ");
                int newStatusChoice = scanner.nextInt();
                String newStatus = (newStatusChoice == 1) ?
                                  "want_to_watch" : "watched";
                watchlistDAO.updateStatus(wId, newStatus);
                break;
            case 4:
                watchlistDAO.getAllWatchlist();
                System.out.print("\nEnter Watchlist ID to remove: ");
                int removeId = scanner.nextInt();
                watchlistDAO.removeFromWatchlist(removeId);
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
}