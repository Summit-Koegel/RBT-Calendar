
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
public interface IRedBlackCalendar extends SortedCollectionInterface<ComparableList<IHoliday>> {
        /**
         * This insert method will work the same as the overriden one, except
         * that it will take Holidays instead of List of Holidays, and will
         * add the Holiday to the List if there are multiple on the same date.
         */
        public void insert(IHoliday holiday);

        /**
         * This get method will return a List of all Holidays that will occur
         * on the provided date.
         * @param date A String in the format YYYY-MM-DD
         * @return A List of all Holidays stored with that date. Returns null
         *              if there are no holidays on that date
         */
        public List<IHoliday> get(String date);

        /**
         * This get method will return a List of all Holidays that will occur
         * on and between the provided dates.
         * @param startDate A String in the format YYYY-MM-DD
         * @param endDate A String in the format YYYY-MM-DD
         * @return A List of all Holidays stored with that date. Returns null
         *              if there are no holidays on that date
         */
        public List<IHoliday> get(String startDate, String endDate);

        /**
         * This method will return a List of all Holidays on the day
         * chronologically closest to the current date
         * @return A List of Holidays on the closest date with any holidays
         */
        public List<IHoliday> getNextHolidays();
        
        public void remove(IHoliday holiday);
}

