import java.util.List;

/**
 * An instance of a classs that implements the following interface can
 * be used to search and retrieve the database of holidays within the 
 * HolidayCalendarApp
 */
public interface IHolidayCalendarBackend {

    public void addHoliday(IHoliday holiday); // adds a holiday to the RBT
    
    public void boolRange(boolean range); // checks if the program requires a range(i.e one date)
    public void setRange(String date1, String date2); // sets the range determined by the two given dates
    public String getRange(); // retrieves the range
    
    // Search depending on one or two dates
    public List<IHoliday> searchBySingleDate(String date);
    public List<IHoliday> searchByTwoDates(String date1, String date2);
}
