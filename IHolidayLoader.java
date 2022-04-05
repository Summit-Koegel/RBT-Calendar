import java.io.FileNotFoundException;
import java.util.List;


/**
 * Instances of classes that implement this interface can be used to load a 
 * list of holidays from a specified XML file
 * The following XML attributes define each holiday:
 *   - Name: the name of the holiday
 *   - Year: the year of that holiday
 *   - Month: the month of that holiday
 *   - Day: the day of that holiday
 *   - Day of the Week: the day of the week that the holiday falls on
 */
public interface IHolidayLoader {

    /**
     * This method loads the holidays listed in the XML file
     * @param filepath is the file path of the XML file
     * @return a list of Holiday objects from the XML file
     */
    List<IHoliday> loadHolidays(String filepath) throws FileNotFoundException;
	
}
