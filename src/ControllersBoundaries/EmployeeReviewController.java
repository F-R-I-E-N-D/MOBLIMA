package ControllersBoundaries;

import CineplexClasses.CineplexController;

public class EmployeeReviewController implements IReviewDeletable, IReviewViewable
{
	public void removeReview (CineplexController cineplexGroup, int movieID, int reviewID)
    {
    	cineplexGroup.getMovieList().get(movieID).deleteReview(reviewID);
    }
    
    public void printAvgRating (CineplexController cineplexGroup, int movieID)
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
