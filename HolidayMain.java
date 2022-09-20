import java.util.ArrayList;

public class HolidayMain {
    public static void main(String args[]){
        List<IHoliday> list = new ArrayList<>();
        HolidayLoader newLoader= new HolidayLoader();
        newLoader.loadHolidays("Holidays.xml");

        HolidayCalendarBackend backend = new HolidayCalendarBackend();

        for(IHoliday holiday : list) backend.addHoliday(holiday);
        IHolidayCalendarFront frontend = new HolidayCalendarFront(backend);
        frontend.exec();



    }
}
