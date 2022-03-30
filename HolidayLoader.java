import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

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

        // instantiate factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            // parse file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            // store all holidays in the XML file
            NodeList list = doc.getElementsByTagName("holiday");
            // create new Holiday for each holiday in the list
            for(int i = 0; i < list.getLength(); i++) {
                // store this holiday as a node, then convert it to an element
                Node node = list.item(i);
                Element element = (Element) node;
                // add a new Holiday to the list
                holidays.add(new Holiday(
                        element.getElementsByTagName("Name").item(0).getTextContent(),
                        element.getElementsByTagName("WeekDay").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("Year").item(0).getTextContent()),
                        Integer.parseInt(element.getElementsByTagName("Month").item(0).getTextContent()),
                        Integer.parseInt(element.getElementsByTagName("Date").item(0).getTextContent())
                ));
            }
            return holidays;
        } catch (Exception e) {
            return holidays; // return empty list (or whatever is already inserted)
        }
    }

}
