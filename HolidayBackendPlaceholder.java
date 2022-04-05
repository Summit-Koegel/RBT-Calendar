
public class HolidayBackendPlaceholder implements IHoliday{
  
  private String Name;
  private int Year;
  private int Month;
  private int Day;
  private String DayOfWeek;
  
  public HolidayBackendPlaceholder(String Name, int Year, int Month,int Day, String DayOfWeek) {
    this.Name = Name;
    this.Year = Year;
    this.Month = Month;
    this.Day = Day;
    this.DayOfWeek = DayOfWeek;
}

  @Override
  public String getName() {
    return Name;
  }

  @Override
  public int getYear() {
    return Year;
  }

  @Override
  public int getMonth() {
    return Month;
  }

  @Override
  public int getDay() {
    return Day;
  }

  @Override
  public String getDayOfWeek() {
    return DayOfWeek;
  }

  @Override
  public int compareTo(IHoliday other) {
    if(other.getYear() == Year){
      if(other.getMonth() == Month){
        return Day - other.getDay();
      }
      return Month - other.getMonth();
    }
    return Year - other.getYear();
  }

}
