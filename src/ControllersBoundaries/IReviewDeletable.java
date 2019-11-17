package ControllersBoundaries;

import CineplexClasses.CineplexController;

public interface IReviewDeletable 
{
	public void removeReview (CineplexController cineplexGroup, int movieID, int reviewID);
}
