package CineplexClasses;
import java.io.*;
import java.util.ArrayList;

public class Movie implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Genre {ACTION, COMEDY, FANTASY, HORROR, ROMANCE, MUSICAL, OTHERS}
    public enum Type {THREE_D, BLOCKBUSTER, STANDARD, BLOCKBUSTER_3D}
    public enum ShowingStatus {COMING_SOON, PREVIEW, NOW_SHOWING, DISCONTINUED}
    public enum Restriction {G, PG, PG13, NC16, M18, R21, NAR}

    private String title;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private String[] cast;
    private Type type;
    private Genre genre;
    private int MovieId;
    private ArrayList<MovieReview> MovieReviewAndRatingList;

    public Movie(int MovieId, String title, String synopsis, String[] cast, String director, Genre genre, Type type) {
        
    	this.setTitle(title);
        this.setCast(cast);
        this.setSynopsis(synopsis);
        this.setDirector(director);
        this.MovieId = MovieId;
        MovieReviewAndRatingList = new ArrayList<>();
        showingStatus = ShowingStatus.COMING_SOON; // Assume movie added when it is 'coming soon'
        this.genre = genre;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int movieId) {
        MovieId = movieId;
    }

    public ArrayList<MovieReview> getMovieReviewAndRatingList() {
        return MovieReviewAndRatingList;
    }

    public void setMovieReviewAndRatingList(ArrayList<MovieReview> movieReviewAndRatingList) {
        MovieReviewAndRatingList = movieReviewAndRatingList;
    }

    public void addReview(String review, int rating, int userId) {
        if (rating < 0 || rating > 5) {
            System.out.println("Error! Rating is not between 1 and 5 inclusive!");
            return;
        }
        MovieReviewAndRatingList.add(new MovieReview(MovieReviewAndRatingList.size(), userId, review, rating));
    }

    // Prompts user input for index value of review to be deleted
    public void deleteReview(int reviewID) {
    	
    	if (reviewID <0 || reviewID>=MovieReviewAndRatingList.size())
    	{
    		System.out.println("Invalid Index!");
            return;
    	}
        MovieReviewAndRatingList.remove(reviewID);
        System.out.println("Review at index " + reviewID + " is deleted.");
    }

    public double getAvgRating() 
    {
    	if (MovieReviewAndRatingList.size()<=1)
    		return -1;
    	
    	double avg =0;
    	int count = 0;
    	for (MovieReview review : MovieReviewAndRatingList)
    	{
    		avg += review.getRating();
    		count++;
    	}
    	
    	return avg/count;
    }
    
    // Print all reviews and ratings
    public void printReviewsAndRatings() {
        if (MovieReviewAndRatingList.isEmpty()) {
            System.out.println("There are no reviews for this movie.");
            return;
        }
        for (int i = 0; i < MovieReviewAndRatingList.size(); i++) {
            System.out.println("Rating " + i + ": " + MovieReviewAndRatingList.get(i).getRating());
            System.out.println("Review " + i + ": " + MovieReviewAndRatingList.get(i).getReview());
        }
    }
}




/*
import java.util.*;

public class Movie {
	
	private String name;w
	private String description;
	private String [] cast;
	private Genre genre;
	private int id;
	private ArrayList<MovieReview> movieReviewList;
	
	public enum Genre {NA, Horror, Comedy, Romance, Tragedy}
	public enum Type {THREE_D, BLOCKBUSTER, STANDARD}
	public enum Restriction {G, PG, PG13, NC16, M18, R21, NAR}
	
	public Movie (int id, String name, String description, String [] cast, Genre genre)
	{
		this.setMovieName(name);
		this.setMovieCast(cast);
		this.setMovieDescription(description);
		this.setMovieId(id);
		this.setGenre(genre);
		movieReviewList = new ArrayList<MovieReview>();
	}
	
	public Movie (int id, String name, String description, String [] cast)
	{
		this.setMovieName(name);
		this.setMovieCast(cast);
		this.setMovieDescription(description);
		this.setMovieId(id);
		this.setGenre(Genre.NA);
		movieReviewList = new ArrayList<MovieReview>();
	}

	public String getMovieName() {
		return name;
	}

	public void setMovieName(String name) {
		this.name = name;
	}

	public String getMovieDescription() {
		return description;
	}

	public void setMovieDescription(String description) {
		this.description = description;
	}

	public String [] getMovieCast() {
		return cast;
	}

	public void setMovieCast(String [] cast) {
		this.cast = cast;
	}
	
	public int getMovieId(){
	    return id;
	   }
	
	public void setMovieId(int idnew)
	{
	    id = idnew;
	}
	
	public void addReview(String review, int rating){
	    movieReviewList.add(new MovieReview(movieReviewList.size(), review, rating));
	  }
	
	public void deleteReview(){
	    movieReviewList.add(new MovieReview(movieReviewList.size(), review, rating));
	  }

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
}
*/