package ControllersBoundaries;

import CineplexClasses.CineplexController;

public interface IReviewAddable 
{
	public void createReview(CineplexController cineplexGroup, int movieID, String review, int rating, String userID);
}
