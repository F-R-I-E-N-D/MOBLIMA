package ControllersBoundaries;

import CineplexClasses.CineplexGroup;

public class CustomerReviewController implements IReviewAddable, IReviewViewable
{
	public void createReview(CineplexGroup cineplexGroup, int movieID, String review, int rating, String userID)
    {
    	if (rating<=5 && rating>=1)
    	{
    		cineplexGroup.getMovieList().get(movieID).addReview(review, rating, userID);
    	}    		
    	else
    		System.out.println("Rating must be 1 and 5 (inclusive)");
    }
	
	public void printAvgRating (CineplexGroup cineplexGroup, int movieID)
    {
    	double avg = cineplexGroup.getMovieList().get(movieID).getAvgRating();
    	if (avg==-1)
    	{
    		System.out.println("Not enough reviews to return for \"" + cineplexGroup.getMovieList().get(movieID).getTitle() + "\"");
    	}
    	else
    	{
    		System.out.println("Average rating for \"" + cineplexGroup.getMovieList().get(movieID).getTitle() + "\" = " + avg);
    	}
    }
}
