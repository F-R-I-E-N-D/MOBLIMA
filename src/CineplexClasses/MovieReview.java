package CineplexClasses;
import java.io.*;
public class MovieReview implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int reviewID;
	private String review;
	private int rating;
	private String userID;

    public MovieReview(int reviewID, String userID, String review, int rating)
    {
        this.reviewID = reviewID;
        this.review = review;
        this.rating = rating;
        this.userID = userID;
    }

    public String getReview() 
    {
        return review;
    }

    public int getRating() 
    {
        return rating;
    }

    public void setReview(String review) 
    {
        this.review = review;
    }

    public void setRating(int rating) 
    {
        this.rating = rating;
    }

	public int getReviewID() 
	{
		return reviewID;
	}

	public void setReviewID(int reviewID) 
	{
		this.reviewID = reviewID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}