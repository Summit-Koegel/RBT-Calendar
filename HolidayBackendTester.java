import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
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

    /*
    @Test
    public void testHolidayRange(){
      HolidayCalendarBackend tree = new HolidayCalendarBackend();

      //Name, dow, year, month, day
      Holiday easter = new Holiday("Easter, Monday, 2002, 3, 8");
      Holiday christmas = new Holiday("christmas, Saturday, 2002, 12, 25");
      Holiday fourth = new Holiday("Fourth, Monday, 2002, 7, 4");
      Holiday something = new Holiday("something, Monday, 2002, 8, 9");

      tree.addHoliday(easter);
      tree.addHoliday(christmas);
      tree.addHoliday(fourth);
      tree.addHoliday(something);

      Assertions.assertEquals(0, tree.get("2002-01-01", "2002-02-01").size());

      Assertions.assertEquals(2, tree.get("2002-06-01", "2002-09-01").size());

    }

    @Test
    public void getNextHolidayTest(){
      HolidayCalendarBackend tree = new HolidayCalendarBackend();

      //Name, dow, year, month, day
      IHoliday easter = new Holiday("Easter, Monday, 2002, 3, 8");
      Holiday christmas = new Holiday("christmas, Saturday, 2002, 12, 25");
      Holiday fourth = new Holiday("Fourth, Monday, 2002, 7, 4");
      Holiday something = new Holiday("something, Monday, 2002, 8, 9");

      tree.addHoliday(easter);
      tree.addHoliday(christmas);
      tree.addHoliday(fourth);
      tree.addHoliday(something);

      Assertions.assertEquals("[Fourth: 2002/07/04 (Monday)]", tree.getNextHoliday("2002-03-08"));

    }

    */

    
      //Name, dow, year, month, day
      //IHoliday easter = new Holiday("Easter, Monday, 2002, 3, 8");
/*
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testFE1(){
      IHolidayCalendarFront run = HolidayCalendarFront();
      run.exec();

      Assertions.assertEquals(run.exec(), run.exec().contains("PLEASE SELECT AN OPTION"));


    }

    @Test
    public void test2FE(){
      IHolidayCalendarFront run = HolidayCalendarFront();
      run.exec();

      Assertions.assertEquals(run.exec(), run.exec().contains("[S] - Search for holiday in planner"));
    }
    */
    
    

}
