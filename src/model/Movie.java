package model;
public class Movie {
    
    private int id;
    private String title;
    private String genre;
    private String director;
    private int year;
    private double rating;
    private String description;
    
    // Constructor
    public Movie(int id, String title, String genre, 
                 String director, int year, 
                 double rating, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.description = description;
    }
    
    // Getters
    public int getId() { 
        return id; 
    }
    public String getTitle() {
         return title;
         }
    public String getGenre() {
         return genre; 
        }
    public String getDirector() {
         return director; 
        }
    public int getYear() { 
        return year; 
    }
    public double getRating() { 
        return rating; 
    }
    public String getDescription() { 
        return description; 
    }
    
    // Setters
    public void setTitle(String title) { 
        this.title = title; 
    }
    public void setGenre(String genre) { 
        this.genre = genre; 
    }
    public void setDirector(String director) {
         this.director = director;
         }
    public void setYear(int year) { 
        this.year = year; 
    }
    public void setRating(double rating) { 
        this.rating = rating; 
    }
    public void setDescription(String description) { 
        this.description = description; }
    
    // Display
    @Override
    public String toString() {
        return "\n--- Movie Details ---" +
               "\nID       : " + id +
               "\nTitle    : " + title +
               "\nGenre    : " + genre +
               "\nDirector : " + director +
               "\nYear     : " + year +
               "\nRating   : " + rating + "/10" +
               "\nDesc     : " + description +
               "\n--------------------";
    }
}