//import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class HolidayBackendTester {

  
  @Test
  public void addHolidayTest() {

    HolidayCalendarBackend tree = new HolidayCalendarBackend();

    HolidayBackendPlaceholder a = new HolidayBackendPlaceholder("Easter", 2000, 3, 8, "Sunday");

    tree.addHoliday(a);

    List<IHoliday> result = new ArrayList<>();
    result = tree.searchBySingleDate("2000,03,08");

    Assertions.assertEquals("Easter", result.get(0).getName());


  }


    @Test
    public void getDateTest() {
      HolidayCalendarBackend tree = new HolidayCalendarBackend();

      HolidayBackendPlaceholder a = new HolidayBackendPlaceholder("Easter", 2000, 3, 8, "Sunday");
  
      tree.addHoliday(a);
  
      List<IHoliday> result = new ArrayList<>();
      result = tree.searchBySingleDate("2000,03,08");
  
      Assertions.assertEquals(2000, result.get(0).getYear());
      Assertions.assertEquals(3, result.get(0).getMonth());
      Assertions.assertEquals(8, result.get(0).getDay());


    }

    @Test
    public void getDOWTest() {
      HolidayCalendarBackend tree = new HolidayCalendarBackend();

      HolidayBackendPlaceholder a = new HolidayBackendPlaceholder("Easter", 2000, 3, 8, "Sunday");
  
      tree.addHoliday(a);
  
      List<IHoliday> result = new ArrayList<>();
      result = tree.searchBySingleDate("2000,03,08");
  
      Assertions.assertEquals("Sunday", result.get(0).getDayOfWeek());



    }

    @Test
    public void sizeTest() {
      HolidayCalendarBackend tree = new HolidayCalendarBackend();

      HolidayBackendPlaceholder a = new HolidayBackendPlaceholder("Easter", 2000, 3, 8, "Sunday");
  
      tree.addHoliday(a);
  
      List<IHoliday> result = new ArrayList<>();
      result = tree.searchBySingleDate("2000,03,08");
  
      Assertions.assertEquals(1, result.size());

    }

    @Test
    public void searchNextDateTest() {
      HolidayCalendarBackend tree = new HolidayCalendarBackend();

      HolidayBackendPlaceholder a = new HolidayBackendPlaceholder("Easter", 2000, 3, 8, "Sunday");
      HolidayBackendPlaceholder b = new HolidayBackendPlaceholder("Christmas", 2001, 5, 10, "Tuesday");
      HolidayBackendPlaceholder c = new HolidayBackendPlaceholder("Thanksgiving", 2002, 4, 15, "Monday");
  
      tree.addHoliday(a);
      tree.addHoliday(b);
      tree.addHoliday(c);
  
      List<IHoliday> result = new ArrayList<>();
      result = tree.searchNextDate("2001,05,10");
  
      Assertions.assertEquals("Thanksgiving", result.get(0).getName());

    }

}
