import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * Class to load holidays from a given XML file. The XML file
 * contains the following: name of the holiday, year of the holiday,
 * moth of the holiday, date of the holiday, and day of the week
 * that the holiday falls on.
 */
public class HolidayLoader implements IHolidayLoader {

    /**
     * Loads all of the holidays from a given XML file.
     *
     * @param filepath is the filepath of the XML file to read
     * @return a list of Holiday objects contained in the XML file
     */
    @Override
    public List<IHoliday> loadHolidays(String filepath) throws FileNotFoundException {
        // store xml file and ensure filepath is valid
        File xmlFile = new File(filepath);
        if(!xmlFile.exists()) throw new FileNotFoundException("Invalid file path");

        // create list to store holidays
        ArrayList<IHoliday> holidays = new ArrayList<IHoliday>();

        // create scanner to traverse xml file
        Scanner sc = new Scanner(xmlFile);

        // skip first and second line
        sc.nextLine();
        sc.nextLine();

        // initialize useful variables
        int lineCounter = 0; // counter to keep track of what line is being read
        // variables to store parameters for the current holiday object being read
        String name = null;
        String dayOfWeek = null;
        int year = 0;
        int month = 0;
        int day = 0;

        // traverse xml file
        while(sc.hasNext()) { // loop until end of xml file is reached
            // arrays of strings used to store split lines
            String[] data;
            String[] splitData;
            switch(lineCounter % 7) {
                case 0:
                    // line is <Holiday>, skip
                    sc.nextLine();
                    break;
                case 1:
                    // line is <Name>, store name of holiday
                    data = sc.nextLine().split(">");
                    splitData = data[1].split("<");
                    name = splitData[0];
                    break;
                case 2:
                    // line is <Year>, store year of holiday
                    data = sc.nextLine().split(">");
                    splitData = data[1].split("<");
                    year = Integer.parseInt(splitData[0]);
                    break;
                case 3:
                    // line is <Month>, store month of holiday
                    data = sc.nextLine().split(">");
                    splitData = data[1].split("<");
                    month = Integer.parseInt(splitData[0]);
                    break;
                case 4:
                    // line is <Day>, store day of holiday
                    data = sc.nextLine().split(">");
                    splitData = data[1].split("<");
                    day = Integer.parseInt(splitData[0]);
                    break;
                case 5:
                    // line is <WeekDay>, store the day of the week of the holiday
                    data = sc.nextLine().split(">");
                    splitData = data[1].split("<");
                    dayOfWeek = splitData[0];
                    break;
                case 6:
                    // line is </Holiday>, create new Holiday with collected data
                    holidays.add(new Holiday(name, dayOfWeek, year, month, day));
                    sc.nextLine();
                    break;
            }
            lineCounter++;
        }
        return holidays;
    }

}
