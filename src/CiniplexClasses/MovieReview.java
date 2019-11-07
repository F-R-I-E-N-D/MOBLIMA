package CiniplexClasses;

public class MovieReview {
	
	private int reviewID;
	private String review;
	private int rating;
	private int userID;

    public MovieReview(int reviewID, int userID, String review, int rating)
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}