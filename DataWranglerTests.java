import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataWranglerTests {

    // instance
    HolidayLoader _instance;

    /**
     * Called before each test is run. Creates an instance
     * of HolidayLoader to use in the method.
     */
    @BeforeEach
    public void createInstance() {
        _instance = new HolidayLoader();
    }

    /**
     * Tests the constructor for the Holiday class.
     */
    @Test
    public void testHolidayConstructor() {
        // ensures calling the constructor results in a new Holiday object
        assert(new Holiday(null, null, 0, 0, 0) != null);
        assert(new Holiday("Birthday", "Thursday", 2002, 10, 17) != null);
    }

    /**
     * Tests the getter methods in the Holiday class.
     */
    @Test
    public void testHolidayGetters() {
        // test normal constructor
        Holiday holiday = new Holiday("Birthday", "Thursday", 2002, 10, 17);
        assert(holiday.getName().equals("Birthday"));
        assert(holiday.getDayOfWeek().equals("Thursday"));
        assert(holiday.getYear() == 2002);
        assert(holiday.getMonth() == 10);
        assert(holiday.getDay() == 17);
        // test constructor with some null values
        holiday = new Holiday(null, null, 0, 0, 0);
        assert(holiday.getName() == null);
        assert(holiday.getDayOfWeek() == null);
        assert(holiday.getYear() == 0);
        assert(holiday.getMonth() == 0);
        assert(holiday.getDay() == 0);

    }

    /**
     * Tests the toString() method in the Holiday class.
     */
    @Test
    public void testHolidayString() {
        Holiday holiday = new Holiday("Birthday", "Thursday", 2002, 10, 17);
        assert(holiday.toString().equals("Birthday: 2002/10/17 (Thursday)"));
        holiday = new Holiday(null, null, 0, 0, 0);
        assert(holiday.toString().equals("null: 0/0/0 (null)"));
    }

    /**
     * Tests the compareTo() method in the Holiday class
     */
    @Test
    public void testHolidayCompareTo() {
        // test comparison by year
        Holiday holiday0 = new Holiday("Birthday", "Thursday", 2002, 10, 17);
        Holiday holiday1 = new Holiday("Birthday", "Thursday", 2003, 5, 13);
        assert(holiday0.compareTo(holiday1) < 0);
        // test comparison by month
        holiday1 = new Holiday("Birthday", "Thursday", 2002, 11, 17);
        assert(holiday0.compareTo(holiday1) < 0);
        // test comparison by day
        holiday1 = new Holiday("Birthday", "Thursday", 2002, 10, 29);
        assert(holiday0.compareTo(holiday1) < 0);
        // test comparison by name
        holiday1 = new Holiday("Christmas", "Thursday", 2002, 10, 17);
        assert(holiday0.compareTo(holiday1) < 0);
    }

    /**
     * Tests the HolidayLoader class
     */
    @Test
    public void testLoader() {
        // test exception throw with invalid file
        try {
            _instance.loadHolidays("invalid name");
            assert(false); // no exception thrown when expected
        } catch (FileNotFoundException e) {
            assert(true); // expected exception
        } catch (Exception e) {
            assert(false);  // invalid exception
        }

        // test loading with valid file
        try {
            ArrayList<IHoliday> holidayList = (ArrayList<IHoliday>) _instance.loadHolidays("Holidays.xml");
            assert(holidayList.size() == 51); // ensure all holidays are loaded
            // ensure each holiday has expected values
            for(IHoliday holiday : holidayList) {
                assert(
                        holiday.getName() != null &&
                        holiday.getDayOfWeek() != null &&
                        holiday.getDay() > 0 && holiday.getDay() <= 31 &&
                        holiday.getMonth() > 0 && holiday.getMonth() <= 12 &&
                        holiday.getYear() == 2022
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);  // invalid exception
        }
    }

    // Additional Data Wrangler tests in conjunction with other code

    /**
     * Tests the backend's addHoliday method to make sure it uses loaded Holidays
     */

    @Test
    public void testAddHoliday() {
	    HolidayCalendarBackend backend = new HolidayCalendarBackend();
	    try {
		    ArrayList<IHoliday> holidays = _instance.loadHolidays("Holidays.xml");
		    for(IHoliday holiday : holidays) {
			    backend.addHoliday(holiday);
			    assert(true); // correctly inserted
		    }

	    } catch (Exception e) {
		    // invalid exception
		    assert(false);
	    }
    }

    /**
     * Tests the backend's and RedBlackCalendar's response to an invalid Holiday
     */
    @Test
    public void testInvalidHoliday() {
	    HolidayCalendarBackend back = new HolidayCalendarBackend();
	    try {
		    Holiday test = null;
		    back.insert(test); // shouldn't throw error
		    assert(true);
		    test = new Holiday("Fake", "Fake", -203, 45, 1908);
		    back.insert(test); // shouldn't throw error
		    assert(true); 
	    } catch (Exception e) {
		    assert(false);
	    }
    }

    // Code to review: Backend Developer
 
    /**
     * Tests the searchBySingleDate method of HolidayCalendarBackend
     */
    @Test
    public void testSingleDate() {
	HolidayCalendarBackend backend = new HolidayCalendarBackend();
	try {
		// test date with no holiday
		 assert(backend.searchBySingleDate("2022-7-28").size() == 0);
		 // test date with one holiday
		 assert(backend.searchBySingleDate("2022-12-25").size() == 1 &&
				 backend.searchBySingleDate("2022-12-25").get(0).equals(new Holiday("Christmas Day", "Sunday", 2022, 12, 25)));
		 // test date with multiple holidays
		 assert(backend.searchBySingleDate("2022-10-10").size() == 2 && backend.searchBySingleDate("2022-10-10").contains(new Holiday("Columbus Day", "Monday", 2022, 10, 10)));
	} catch (Exception e) {
	    // invalid exception
	    assert(false);
	}
    }

    /**
     * Tests the searchNextDate method of CalendarBackend
     */
    @Test
    public void testNextDate() {
	    HolidayCalendarBackend backend = new HolidayCalendarBackend();
	    try {
		    // check individual date
		    assert(backend.searchNextDate("2022-10-29").size() == 1 && backend.searchNextDate("2022-10-29").get(0).equals(new Holiday("Halloween", "Monday", 2022, 10, 31)));
		    // check all possible dates return something
		    for(int month = 1; month <= 12; month++) {
			    for(int day = 1; day <= 31; day++) {
				    String date = "2022"+"-"+month+"-"+day;
				    assert(backend.searchNextDate(date).size() != 0);
			    }
		    }
	    } catch (Exception e) {
		    assert(false); // invalid exception
	    }
    }

}
