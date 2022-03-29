import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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

}
