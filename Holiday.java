
/**
 * Instantiable class to represent a Holiday that contains
 * a name, date, and day of the week.
 */
public class Holiday implements IHoliday {

    // instance variables
    protected String name;
    protected String weekDay;
    protected int year;
    protected int month;
    protected int day;

    /**
     * Constructor that creates a new Holiday object and stores
     * the data for it.
     *
     * @param name is the name of the Holiday
     * @param weekDay is the day of the week for the Holiday
     * @param year is the year the Holiday is in
     * @param month is the month the Holiday is in
     * @param day is the date the Holiday falls on
     */
    public Holiday(String name, String weekDay, int year, int month, int day) {
        this.name = name;
        this.weekDay = weekDay;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Gets the name of this holiday.
     *
     * @return the name of the holiday
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the year of this holiday.
     *
     * @return the year of the holiday
     */
    @Override
    public int getYear() {
        return this.year;
    }

    /**
     * Gets the month of this holiday.
     *
     * @return the month of the holiday
     */
    @Override
    public int getMonth() {
        return this.month;
    }

    /**
     * Gets the date of this holiday.
     *
     * @return the day of the month of the holiday
     */
    @Override
    public int getDay() {
        return this.day;
    }

    /**
     * Gets the day of the week of this holiday.
     *
     * @return the day of the week of the holiday
     */
    @Override
    public String getDayOfWeek() {
        return this.weekDay;
    }

    /**
     * Compares two holidays based on the date and name
     *
     * @param other is the Holiday to compare with
     * @return a positive integer if this holiday comes after
     * the other holiday (or is alphabetically last if they are on
     * the same date), 0 if the holidays are identical, or a negative
     * integer if this holiday comes before the other holiday (or
     * is alphabetically first if they are on the same date)
     */
    @Override
    public int compareTo(IHoliday other) {
        if(this.getYear() - other.getYear() != 0) return this.getYear() - other.getYear(); // compare by year first
        else if(this.getMonth() - other.getMonth() != 0) return this.getMonth() - other.getMonth(); // then month
        else if(this.getDay() - other.getDay() != 0) return this.getDay() - other.getDay(); // then day
        else return this.getName().compareTo(other.getName()); // if dates are the same, compare names
    }

    /**
     * Converts the holiday's data to a readable String
     *
     * @return a string in the form "NAME: YYYY-MM-DD (WEEKDAY)"
     */
    @Override
    public String toString() {
        return this.getName() + ": " + this.getYear() + "/" + this.getMonth() + "/" + + this.getDay() + " (" + this.getDayOfWeek() + ")";
    }



}
