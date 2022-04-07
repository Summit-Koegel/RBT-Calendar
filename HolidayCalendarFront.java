// --== CS400 Project 02 File Header ==--
// Name: Aaryush Gupta
// CSL Username: aaryush
// Email: agupta276@wisc.edu
// Lecture #: 004
// Notes to Grader: <Notes>

import java.util.List;
import java.util.Scanner;

/**
 * This class implements IHolidayFront Interface to provide a working frontend for the Holiday
 * project
 */
public class HolidayCalendarFront implements IHolidayCalendarFront {
  private HolidayCalendarBackend backend = new HolidayCalendarBackend();
  private Scanner sc = new Scanner(System.in);

  public static void main(String[] args){
    IHolidayCalendarFront run = new HolidayCalendarFront();
    run.exec();
  }
  @Override public void exec() {
    boolean loop = true;
    String input;
    System.out.println("LOADING HOLIDAY PLANNER.\nPLEASE SELECT AN OPTION");
    do {
      System.out.println("[S] - Search for holiday in planner\n[N] - Show next holiday based on "
          + "current data\n[A] - Add new holiday\n[R] - Show holiday in a range of dates\n[Q] - "
          + "Quit");
      input = sc.nextLine().toLowerCase();
      switch (input) {
        case "s":
          search();
          break;
        case "n":
          next();
          break;
        case "a":
          addHoliday();
          break;
        case "r":
          range();
          break;
        case "q":
          loop = false;
          break;
        default:
          System.out.println("Invalid input, try again: ");
      }
    } while (loop);
  }

  @Override public void addHoliday() {
    System.out.println("--Selected: Add new holiday--\nPlease give the name of the holiday: ");
    String name = sc.nextLine();
    System.out.println("Please give the date of holiday: ");
    String date = sc.nextLine();
    System.out.println("Please enter weekday of Holiday");
    String wday = sc.nextLine();

    backend.addHoliday(new Holiday(name, wday, Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10))));
  }

  @Override public void search() {
    System.out.println("--Selected: Search for holiday --\nPlease give date (YYYY-MM-DD)");
    System.out.println(backend.searchBySingleDate(sc.nextLine()));
  }

  @Override public void next() {
    System.out.println("--Selected: Show next holiday--\nPlease give current date (YYYY-MM-DD)");
    String date = sc.nextLine();
    System.out.println(backend.getNextHoliday(date));
  }

  @Override public void range() {
    System.out.println("--Selected: Show range of holidays--\nPlease give start date (YYYY-MM-DD): ");
    String date1 = sc.nextLine();
    System.out.println("Please give end date: ");
    String date2 = sc.nextLine();
    System.out.println(backend.holidayRange(date1, date2));
  }
}
