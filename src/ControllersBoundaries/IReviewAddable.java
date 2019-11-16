package ControllersBoundaries;

import CineplexClasses.CineplexGroup;

public interface IReviewAddable 
{
	public void createReview(CineplexGroup cineplexGroup, int movieID, String review, int rating, String userID);
}
