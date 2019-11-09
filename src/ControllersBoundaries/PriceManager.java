package ControllersBoundaries;

import java.io.Serializable;

import CineplexClasses.Cinema;
import CineplexClasses.Movie;
import CineplexClasses.Show;

public class PriceManager implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double studentMarkdown;
    private double elderlyMarkdown;
    private double weekdayMarkup;
    private double weekendMarkup;
    private double publicHolidayMarkup;
    private double threeDMarkup;
    private double blockbusterMarkup;
    private double standardPrice;
    private double goldClassMarkup;
    private double deluxeClassMarkup;
    private double geminiClassMarkup;
    private double maxClassMarkup;

    public double getPrice(Show.DayType day, int time,
    			boolean student, boolean elderly, Cinema.ClassType classType, Movie.Type movieType) 
    {
        double calculatePrice = standardPrice;
        switch (day) 
        {
            case WEEKDAY:
                calculatePrice += weekdayMarkup;
                break;
            case WEEKEND:
                calculatePrice += weekendMarkup;
                break;
            case PUBLIC_HOLIDAY:
                calculatePrice += publicHolidayMarkup;
                break;
        }

        switch (classType) 
        {
            case STANDARD:
                if (time < 1800) {
                    if (student)
                        calculatePrice -= studentMarkdown;
                    else if (elderly)
                        calculatePrice -= elderlyMarkdown;
                }
                break;
            case GOLD:
                calculatePrice += goldClassMarkup;
                break;
            case DELUXE:
                calculatePrice += deluxeClassMarkup;
                break;
            case GEMINI:
                calculatePrice += geminiClassMarkup;
                break;
            case MAX:
                calculatePrice += maxClassMarkup;
                break;
        }

        switch (movieType) 
        {
            case STANDARD:
                return calculatePrice;
            case BLOCKBUSTER:
                calculatePrice += blockbusterMarkup;
                break;
            case THREE_D:
                calculatePrice += threeDMarkup;
                break;
            case BLOCKBUSTER_3D:
                calculatePrice += blockbusterMarkup + threeDMarkup;
                break;
        }
        return calculatePrice;
    }

    public double getStudentMarkdown() {
        return studentMarkdown;
    }

    public void setStudentMarkdown(double studentMarkdown) {
        this.studentMarkdown = studentMarkdown;
    }

    public double getElderlyMarkdown() {
        return elderlyMarkdown;
    }

    public void setElderlyMarkdown(double elderlyMarkdown) {
        this.elderlyMarkdown = elderlyMarkdown;
    }

    public double getWeekdayMarkup() {
        return weekdayMarkup;
    }

    public void setWeekdayMarkup(double weekdayMarkup) {
        this.weekdayMarkup = weekdayMarkup;
    }

    public double getWeekendMarkup() {
        return weekendMarkup;
    }

    public void setWeekendMarkup(double weekendMarkup) {
        this.weekendMarkup = weekendMarkup;
    }

    public double getPublicHolidayMarkup() {
        return publicHolidayMarkup;
    }

    public void setPublicHolidayMarkup(double publicHolidayMarkup) {
        this.publicHolidayMarkup = publicHolidayMarkup;
    }

    public double getThreeDMarkup() {
        return threeDMarkup;
    }

    public void setThreeDMarkup(double threeDMarkup) {
        this.threeDMarkup = threeDMarkup;
    }

    public double getBlockbusterMarkup() {
        return blockbusterMarkup;
    }

    public void setBlockbusterMarkup(double blockbusterMarkup) {
        this.blockbusterMarkup = blockbusterMarkup;
    }

    public double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public double getGoldClassMarkup() {
        return goldClassMarkup;
    }

    public void setGoldClassMarkup(double goldClassMarkup) {
        this.goldClassMarkup = goldClassMarkup;
    }

    public double getDeluxeClassMarkup() {
        return deluxeClassMarkup;
    }

    public void setDeluxeClassMarkup(double deluxeClassMarkup) {
        this.deluxeClassMarkup = deluxeClassMarkup;
    }

    public double getGeminiClassMarkup() {
        return geminiClassMarkup;
    }

    public void setGeminiClassMarkup(double geminiClassMarkup) {
        this.geminiClassMarkup = geminiClassMarkup;
    }

    public double getMaxClassMarkup() {
        return maxClassMarkup;
    }

    public void setMaxClassMarkup(double maxClassMarkup) {
        this.maxClassMarkup = maxClassMarkup;
    }
}