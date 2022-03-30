// --== CS400 Project [NUMBER] File Header ==--
// Name: Aaryush Gupta
// CSL Username: aaryush
// Email: agupta276@wisc.edu
// Lecture #: 004
// Notes to Grader: <Notes>


import java.util.List;

public interface IHolidayCalendarBackend {

  public void addHoliday(IHoliday holiday); // adds a holiday to the RBT
  public void boolRange(boolean range); // checks if the program requires a range(i.e one date)
  public void setRange(String date1, String date2); // sets the range determined by the two given dates
  public String getRange(); // retrieves the range
  // Search depending on one or two dates
  public List<IHoliday> searchBySingleDate(String date);
  public List<IHoliday> searchByTwoDates(String date1, String date2);

}
