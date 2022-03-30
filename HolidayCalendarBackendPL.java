// --== CS400 Project [NUMBER] File Header ==--
// Name: Aaryush Gupta
// CSL Username: aaryush
// Email: agupta276@wisc.edu
// Lecture #: 004
// Notes to Grader: <Notes>


import java.sql.Array;
import java.util.List;
import java.util.LinkedList;

public class HolidayCalendarBackendPL implements IHolidayCalendarBackend{
  @Override public void addHoliday(IHoliday holiday) {
    System.out.println("Holiday added");
  }

  @Override public void boolRange(boolean range) {
    System.out.println("Range Check");
  }

  @Override public void setRange(String date1, String date2) {
    System.out.println("Range set");
  }

  @Override public String getRange() {
    return null;
  }

  @Override public List<IHoliday> searchBySingleDate(String date) {
    LinkedList<IHoliday> result = new LinkedList<IHoliday>();
    result.add(new HolidayPL("search", "2022/01/01"));
    return result;
  }

  @Override public List<IHoliday> searchByTwoDates(String date1, String date2) {
    LinkedList<IHoliday> result = new LinkedList<IHoliday>();
    result.add(new HolidayPL("2 dates", "2022/02/02"));
    return result;
  }
}
