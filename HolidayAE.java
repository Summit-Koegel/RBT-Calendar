/**
 * placeholder class
 * @author charlie jungwirth
 *
 */
public class HolidayAE implements IHoliday{

	public String name;
	public int year;
	public int month;
	public int day;
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public String toString() {
		return name;
	}
	@Override
	public int getYear() {
		// TODO Auto-generated method stub
		return year;
	}

	@Override
	public int getMonth() {
		// TODO Auto-generated method stub
		return month;
	}

	@Override
	public int getDay() {
		// TODO Auto-generated method stub
		return day;
	}

	@Override
	public String getDayOfWeek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(IHoliday other) {
		HolidayAE othero =(HolidayAE)other;
		// TODO Auto-generated method stub
		return year*13*32+month*32+day-othero.year*13*32-othero.month*32-othero.day;
	}
	public HolidayAE(int mmonth, int mday,int myear, String mname) {
		month = mmonth;
		day = mday;
		year = myear;
		name = mname;
		
	}

}
