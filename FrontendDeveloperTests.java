import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontendDeveloperTests {
	private IHolidayCalendarFront tester = new HolidayCalendarFront();
	private IHoliday holiday = new Holiday("Special", "Tuesday", 2022, 1, 1);

	@BeforeEach
	public void init(){
		tester = new HolidayCalendarFront();
 	 }

	@Test
	public void testType(){
		assertTrue(tester instanceof IHolidayCalendarFront);
 	 }

	/*
	 * Tests if Holiday Constuctor works as intended
	 */
	@Test
	public void testHolidayConstructor(){
		assertEquals(holiday.getYear(), 2022);
		assertEquals(holiday.getMonth(), 1);
		assertEquals(holiday.getName(), "Special");
	}


	/*
	 * Tests Holiday's compareTo
	 */
	@Test
	public void testHolidayCompare(){
		IHoliday test = new Holiday("Test", "Monday", 2021, 1, 1);
		assertEquals(holiday.compareTo(test), 1);	
	}


}
