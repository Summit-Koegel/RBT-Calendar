// --== CS400 Project 02 File Header ==--
// Name: Aaryush Gupta
// CSL Username: aaryush
// Email: agupta276@wisc.edu
// Lecture #: 004
// Notes to Grader: I was unable to find a way to mimic user input in JUnit without using
// libraries I did not understand (and therefore thought dishonest to use).

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontendDeveloperTests {
	private IHolidayCalendarFront tester = new HolidayCalendarFront();

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
		IHoliday holiday = new Holiday("Special", "Tuesday", 2022, 1, 1);
		assertEquals(holiday.getYear(), 2022);
		assertEquals(holiday.getMonth(), 1);
		assertEquals(holiday.getName(), "Special");
	}


	/*
	 * Tests Holiday's compareTo
	 */
	@Test
	public void testHolidayCompare(){
		
	}


}
