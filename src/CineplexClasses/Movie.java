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
    private int movieSales;

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
        this.setMovieSales(0);
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

    public void addReview(String review, int rating, String userId) {
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

	public int getMovieSales() 
	{
		return movieSales;
	}

	public void setMovieSales(int movieSales) 
	{
		this.movieSales = movieSales;
	}
	
	public void addMovieSales(int numTickets)
	{
		this.movieSales+=numTickets;
		
	}
}