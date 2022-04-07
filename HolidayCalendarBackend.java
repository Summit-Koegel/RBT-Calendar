import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implements of IHolidayCalendarBackend using RBT data structure
 *
 *
 */
public class HolidayCalendarBackend implements IHolidayCalendarBackend {

  private RedBlackCalendar tree;
  
  public HolidayCalendarBackend() {
    tree = new RedBlackCalendar();
    
  }

  @Override
  public void addHoliday(IHoliday holiday) {

    tree.insert(holiday);
    

  }

  @Override
  public List<IHoliday> searchBySingleDate(String date) {
    List<IHoliday> result = new ArrayList<>();
    Iterator<IHoliday> iteratorTree = tree.IteratorHol();
    String[] split = date.trim().split("-");
    while (iteratorTree.hasNext()) {
      IHoliday iterator = iteratorTree.next();
      if (Integer.valueOf(split[0]) == (iterator.getYear())) {
        if (Integer.valueOf(split[1]) == (iterator.getMonth())) {
          if (Integer.valueOf(split[2]) == (iterator.getDay())) {
            result.add(iterator);
          }
        }
      }
    }
    return result;
  }
  
  public List<IHoliday> searchNextDate(String date) {
    List<IHoliday> result = new ArrayList<>();
    Iterator<IHoliday> iteratorTree = tree.IteratorHol();
    String[] split = date.trim().split("-");
    while (iteratorTree.hasNext()) {
      IHoliday next = iteratorTree.next();
      if (Integer.valueOf(split[0]) == (next.getYear())) {
        if (Integer.valueOf(split[1]) == (next.getMonth())) {
          if (Integer.valueOf(split[2]) == (next.getDay())) {
            IHoliday nextdate = iteratorTree.next();
            result.add(nextdate);
          }
        }
      }
    }
    
    return result;
    
  }

  public List<IHoliday> getNextHoliday(String date){
    return tree.getNextHolidays(date);
  }

  public List<IHoliday> holidayRange(String startDate, String endDate){
    return tree.get(startDate, endDate);
  }

  @Override
  public List<IHoliday> searchByTwoDates(String date1, String date2) {
    return tree.get(date1, date2);
  }

  @Override
  public String getRange(){
	return null;
  }

  @Override 
  public void setRange(String date1, String date2){
	// Do nothing 
  }

  @Override 
  public void boolRange(boolean a){
	// Do nothing	 
  } 
}
