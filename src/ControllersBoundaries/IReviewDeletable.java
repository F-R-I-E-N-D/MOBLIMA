package ControllersBoundaries;

import CineplexClasses.CineplexGroup;

public interface IReviewDeletable 
{
	public void removeReview (CineplexGroup cineplexGroup, int movieID, int reviewID);
}
