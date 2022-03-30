/**
 * An implementation of this interface allows for the creation of a console-based frontend to
 * add, search, and find ranges of holidays
 */
public interface IHolidayCalendarFront {
  public void exec();
  public void addHoliday();
  public void search();
  public void next();
  public void range();
}
