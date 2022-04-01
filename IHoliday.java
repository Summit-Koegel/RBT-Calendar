/**
 * Instances of classes that implement this interface represent a single 
 * holiday object that can be stored and sorted based on the date
 */
public interface IHoliday extends Comparable<IHoliday> {

    String getName(); // returns the name of the holiday
    int getYear(); // returns the year of the holiday (4 digits)
    int getMonth(); // returns the month of the holiday
    int getDay(); // returns the day of the holiday
    String getDayOfWeek(); // returns the day of the week that the holiday falls on
    int compareTo(IHoliday other); // compares two holidays by year, then month, then day
    @Override
    String toString(); // returns a date in the format "NAME: YYYY-MM-DD (DOW)"

}