package CiniplexClasses;

import java.util.Arrays;

public class TestApplication {

	public static void main(String[] args) {
		
		CineplexGroup goldenVillage = new CineplexGroup();
		
		goldenVillage.addCineplexToList("GV @Dhoby Ghaut");
		goldenVillage.addCineplexToList("GV @AMK Hub");
		
		int [] seat_config1 = {3,4,3};
		int [] seat_config2 = {5,6,7};
		goldenVillage.createCinema(0, "Hall 1", 3, seat_config1, Cinema.ClassType.DELUXE);
		goldenVillage.createCinema(0, "Gold Class", 5, seat_config2, Cinema.ClassType.GOLD);
		goldenVillage.createCinema(1, "Theatre 1", 3, seat_config1, Cinema.ClassType.STANDARD);
		goldenVillage.createCinema(1, "Theatre 2", 5, seat_config2, Cinema.ClassType.STANDARD);
		
		String [] cast1 = {"Ong Jia Xiang", "Lim Yu Jie"} ;
//		goldenVillage.addMovieToList(title, synopsis, cast, director, genre, type);
		goldenVillage.addMovieToList("Life of a Nerd", "So obvious ... don't you geddit ?", cast1, "Emily Lua", Movie.Genre.FANTASY, Movie.Type.STANDARD);
		String [] cast2 = {"Adi VH", "God"} ;
		goldenVillage.addMovieToList("Burning Wreck", "An epic tale of woe", cast2, "Priyakanshi Bansal", Movie.Genre.HORROR, Movie.Type.THREE_D);
		
		for (Cineplex cplex : goldenVillage.getCineplexList())
		{
			System.out.println(cplex.getCineplexId()+": "+cplex.getCineplexName());
			for (Cinema c : goldenVillage.getCinemaList(cplex.getCineplexId()))
			{
				System.out.println(c.getHallId() + ": " + c.getName() + " | "  + c.getNumRows() + " rows | Config: " + Arrays.toString(c.getColumn()));
				System.out.println("Class: " + c.getClasstype());
			}
			System.out.println("=============================");
		}
		
		for (Movie m :goldenVillage.getMovieList())
		{
			System.out.println("Movie ID: " + m.getMovieId());
			System.out.println("Title: " +m.getTitle());
			System.out.println("Synopsis: " +m.getSynopsis());
			System.out.println("Director: " + m.getDirector());
			System.out.println("Cast:");
			for (String member: m.getCast())
			{
				System.out.println(member);
			}
			System.out.println("=============================");
		}
		
		try
		{
		//goldenVillage.createShow(cineplexID, cinemaID, movieID, time_start, time_end);
		Show.DayType weekday = Show.DayType.WEEKDAY;
		Show.DayType weekend = Show.DayType.WEEKEND;
		Show.DayType public_hol = Show.DayType.PUBLIC_HOLIDAY;
		
		goldenVillage.createShow(0, 1, 0, 1900, 2100, weekday);
		goldenVillage.createShow(0, 1, 1, 2200, 2400, weekday);
		goldenVillage.createShow(0, 0, 0, 1600, 1800, public_hol);
		goldenVillage.createShow(0, 0, 1, 1900, 2100, weekend);
		goldenVillage.createShow(0, 0, 0, 2200, 2400, weekend);
		
		goldenVillage.createShow(1, 1, 0, 900, 1100, weekday);
		goldenVillage.createShow(1, 1, 1, 1200, 1000,weekday);
		goldenVillage.createShow(1, 1, 0, 1600, 1800,weekday);
		goldenVillage.createShow(1, 0, 1, 1700, 1900, public_hol);
		goldenVillage.createShow(1, 0, 0, 2200, 2400, public_hol);
		goldenVillage.createShow(1, 2, 0, 2200, 2400, weekend);
		}
		catch (java.lang.IndexOutOfBoundsException e)
		{
			System.out.println("Invalid Index Entered"); // Okay if used for each createshow statement
		}
		
		for (Cineplex cplex : goldenVillage.getCineplexList())
		{
			System.out.println("\n"+cplex.getCineplexId()+": "+cplex.getCineplexName());
			
			for (Show show: cplex.getShowList())
			{
				System.out.println("\nShowID:\t\t"+ show.getShowID());
				System.out.println("Movie Name:\t" + show.getMovie().getTitle());
				System.out.println("Director:\t" + show.getMovie().getDirector());
				System.out.println("Cinema:\t\t" + show.getHall().getName());
				System.out.println("Day Type:\t" + show.getDaytype());
				System.out.println("Start time:\t" + show.getTime_start() + " Hours");
				System.out.println("Ending time:\t" + show.getTime_end() + " Hours");
				
			}
			
			System.out.println("=============================");
		}
		
//		goldenVillage.createReview(movieID, review, rating, userID);
		goldenVillage.createReview(1, "Adi is sooooo hot in this movie", 5, 0);
		goldenVillage.createReview(1, "Adi is sooooo ugly in this movie", 2, 3);
		goldenVillage.createReview(1, "God is sooooo great in this movie", 3, 4);
		
		goldenVillage.createReview(0, "James is a born actor", 5, 4);
		
		goldenVillage.printAvgRating(1);
		goldenVillage.printAvgRating(0);
		
		System.out.println("=============================");
		for (Movie m : goldenVillage.getMovieList())
		{
			System.out.println("\nMovie:\t" + m.getTitle());
			for (MovieReview review : goldenVillage.getReviews(m.getMovieId()))
			{
				System.out.println("==============");
				System.out.println("ID:\t" + review.getReviewID());
				System.out.println("UserID:\t" + review.getUserID());
				System.out.println("Review:\t" + review.getReview());
				System.out.println("Rating:\t" + review.getRating());
			}
		}
		
		System.out.println("=============================");
		
//		goldenVillage.createReservation(cineplexID, userID, showID, row, lane, seat);
		goldenVillage.createReservation(1, 1, 3, 'C', 2, 2);
		goldenVillage.createReservation(1, 1, 3, 'C', 2, 3);
		goldenVillage.createReservation(1, 2, 3, 'C', 2, 4);
		goldenVillage.createReservation(1, 3, 3, 'C', 3, 1);
		goldenVillage.createReservation(1, 4, 3, 'A', 1, 3);
		
		goldenVillage.printShowLayout(1, 3);
		goldenVillage.deleteReservation(1, 3);
		goldenVillage.deleteReservation(1, 2);
		goldenVillage.printShowLayout(1, 3);;
		
	}

}
