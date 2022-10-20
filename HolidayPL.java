public class HolidayPL implements IHoliday{
  public String date;
  public String name;
  public HolidayPL(String name, String date){
    this.date = date;
    this.name = name;
  }

  @Override public String getName() {
    return name;
  }

  @Override public int getYear() {
    return Integer.parseInt(date.substring(0, 4));
  }

  @Override public int getMonth() {
    return Integer.parseInt(date.substring(5, 7));
  }

  @Override public int getDay() {
    return Integer.parseInt(date.substring(8));
  }

  @Override public String getDayOfWeek() {
    return "Today";
  }

  @Override public int compareTo(IHoliday other) {
    return 0;
  }
  @Override public String toString(){
    return "Name: " + name + "\nDate: " + date;
  }
}
