// --== CS400 File Header Information ==--
// Name: Charles Jungwirth
// Email: crjungwirth@wisc.edu
// Team: DQ
// TA: Ilay Raz
// Lecturer: Florian Heimerl
// Notes to Grader: I messed up the adjust after insert,
//and remove doesn't adjust RBT properties
//Red black calendar alone should work

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {
	
	/**
	 * I was having problems with this applied to RBC
	 * tests to see if redblack tree can remember lots of numbers
	 */
	@Test
	public void redBlackTreeStringTest() {
		try {
	    	RedBlackTree<String> test = new RedBlackTree<String>();
	    	for(int m=7; m<16; m++) {//m
				for(int d = 1;d< 17; d++) {
					for(int y = 0; y<3; y++) {
						test.insert(RedBlackCalendar.ymdText(y+2000, m, d));
					}
				}
			}
	    	for(int m=7; m<16; m++) {//m
				for(int d = 1;d< 17; d++) {
					for(int y = 0; y<3; y++) {
						if(!test.contains(RedBlackCalendar.ymdText(y+2000, m, d))) {
							fail("doesn't contain added element: "+RedBlackCalendar.ymdText(y+2000, m, d));
						}
					}
				}
			}
	    	RedBlackTree<IHoliday> test1 = new RedBlackTree<IHoliday>();
			for(int m=6; m<13; m++) {//m
				for(int d = 1;d< 12; d++) {
					for(int y = 0; y<5; y++) {
						test1.insert(new HolidayAE(m,d,y+2000,"m:"+ m+" d: "+d+" y: "+y ));
					}
				}
			}
			
			for(int m=7; m<13; m++) {//m
				for(int d = 1;d< 12; d++) {
					for(int y = 0; y<5; y++) {
						if(!test1.contains(new HolidayAE(m,d,y+2000,"m:"+ m+" d: "+d+" y: "+y ))){
							fail("item should've been found "+RedBlackCalendar.ymdText(2000+y,m,d));
							
						}
						
					}
				}
			}
		}catch(Exception e) {
			fail("unexpected exceptions");
		}
    	
    	assertEquals(true,true);
    	
    }
	
	
	/**
	 * test to see if RBC.get(date) works for 10 different holidays
	 * checks things like multiple on same day, no day on day
	 * and that data is right
	 */
	@Test
	public void testGetDate() {
		try {
			RedBlackCalendar test = new RedBlackCalendar();
			if(test.get("2002-11-03").size()!=0) {
				fail("get is weird when calendar is empty");
			}
			IHoliday[] hol = new IHoliday[11];
			hol[1] = new HolidayAE(11,5,2002,"1");
			hol[2] = new HolidayAE(11,3,2002,"2");//month/day/year/name
			hol[3] = new HolidayAE(11,1,2002,"3");
			hol[4] = new HolidayAE(11,3,2003,"4");
			hol[5] = new HolidayAE(10,2,2002,"5");
			hol[6] = new HolidayAE(10,5,2002,"6");
			hol[7] = new HolidayAE(10,6,2002,"7");
			hol[8] = new HolidayAE(6,2,2002,"8");
			hol[9] = new HolidayAE(6,2,2003,"9");
			hol[10] = new HolidayAE(8,2,2003,"10");
			for(int i =1; i<hol.length; i++) {
				test.insert(hol[i]);
			}
			for(int i =1; i<hol.length; i++) {
				if(!test.get(RedBlackCalendar.holiDate(hol[i])).get(0).getName().equals(""+i)) {
					fail("wrong element returned in get");
				}
			}
			if(test.get("2003-11-05").size() !=0) {
				fail("didn't return empty list when getting nothing");
			}
			test = new RedBlackCalendar();
			for(int m=6; m<13; m++) {//m
				for(int d = 1;d< 13; d++) {
					for(int y = 0; y<5; y++) {
						test.insert(new HolidayAE(m,d,y+2000,"m: "+ m+" d: "+d+" y: "+y ));
					}
				}
			}
			
			
			//all these elements should be on tree
			for(int m=7; m<13; m++) {//m
				for(int d = 1;d< 13; d++) {
					for(int y = 0; y<4; y++) {
						if(test.get(RedBlackCalendar.ymdText(2000+y,m,d)).size() ==0){
							fail("item should've been found "+RedBlackCalendar.ymdText(2000+y,m,d));

						}
						if(!test.get(RedBlackCalendar.ymdText(2000+y,m,d)).get(0).getName()
								.equals("m: "+ m+" d: "+d+" y: "+y)){
							fail("item should've been found "+RedBlackCalendar.ymdText(2000+y,m,d));
						}
					}
				}
			}
			
		
			
		}catch(Exception e) {
			e.printStackTrace();
			fail("unexpected exception: "+e.getMessage());
		}
		assertTrue(true);
	}
	
	/**
	 * tests the getNextHolidays method
	 */
	@Test
	public void testGetNextHolidays() {
		try {
			RedBlackCalendar test = new RedBlackCalendar();
			IHoliday hol1 = new HolidayAE(11,5,2002,"1");
			IHoliday hol2 = new HolidayAE(11,3,2002,"birthday");//month/day/year/name
			IHoliday hol3 = new HolidayAE(11,1,2002,"3");
			IHoliday hol4 = new HolidayAE(11,3,2003,"4");
			IHoliday hol5 = new HolidayAE(10,2,2002,"5");
			test.insert(hol1);
			test.insert(hol2);
			test.insert(hol3);
			test.insert(hol4);
			test.insert(hol5);
			if(!test.getNextHolidays("2002-11-02").get(0).getName().equals("birthday")) {
				fail("wrong result returned for getNextHolidays");
			}
			test.insert(new HolidayAE(11,3,2002,"2"));
			if(test.getNextHolidays("2002-11-02").size() !=2) {
				fail("doesn't allow multiple holidays on one day");
			}
			if(test.getNextHolidays("2003-12-02").size() !=0) {
				fail("if after last holiday doesnt return empty list");
			}
		}catch (Exception e) {
			//e.printStackTrace();
			fail("unexpected exception: "+e.getMessage());
		}
		assertTrue(true);
	}
	
	/**
	 * tests hol1.get method between two dates
	 * relies on insert method as well
	 */
	@Test
	public void testRange() {
		try {
			RedBlackCalendar test = new RedBlackCalendar();
			IHoliday hol1 = new HolidayAE(11,5,2002,"1");
			IHoliday hol2 = new HolidayAE(11,3,2002,"birthday");//month/day/year/name
			IHoliday hol3 = new HolidayAE(11,1,2002,"3");
			IHoliday hol4 = new HolidayAE(11,3,2003,"4");
			IHoliday hol5 = new HolidayAE(10,2,2002,"5");
			IHoliday hol6 = new HolidayAE(10,5,2002,"6");
			IHoliday hol7 = new HolidayAE(10,6,2002,"7");
			IHoliday hol8 = new HolidayAE(6,2,2002,"8");
			IHoliday hol9 = new HolidayAE(6,2,2003,"9");
			IHoliday hol10 = new HolidayAE(8,2,2003,"10");
			test.insert(hol1);
			test.insert(hol2);
			test.insert(hol3);
			test.insert(hol4);
			test.insert(hol5);
			test.insert(hol6);
			test.insert(hol7);
			test.insert(hol8);
			test.insert(hol9);
			test.insert(hol10);
			if(test.get("2003-06-15","2003-07-05").size()!=0) 
				fail("failed to return empty list");
			
			if(test.get("2002-10-05","2003-07-05").size()!=6) 
				fail("wrong size in range search");
			//are they sorted + in range
			List<IHoliday> result =test.get("2002-10-05","2003-07-05");
			for(int i = 0; i<result.size();i++) {
				if(i>0)
				if(result.get(i).compareTo(result.get(i-1))<0) {
					fail("unsorted results");
				}
				if(RedBlackCalendar.holiDate(result.get(i)).compareTo("2002-10-05")<0) {
					fail("date below min");
				}
				if(RedBlackCalendar.holiDate(result.get(i)).compareTo("2003-07-05")>0) {
					fail("date above max");
				}
			}
				
			
		}catch(Exception e) {
			fail("unexpected exception");
		}
		
		assertTrue(true);
	}
	
	/**
	 * tests redBlack calander.remove(IHoliday) method
	 * by first adding 130 unique date holidays
	 * adding one with a repeat, and seeing if it 
	 * still returns the right value
	 * sees if getting a removed value returns the value
	 */
	@Test
	public void removeTest() {
		
		try {
			RedBlackCalendar test = new RedBlackCalendar();
			RedBlackCalendar test1 = new RedBlackCalendar();
			IHoliday[] holidays = new IHoliday[130];
			for(int i = 0; i< 130; i++) {
				holidays[i] = new HolidayAE(7,7,1900+i,""+i);
				test.insert(holidays[i]);
			}
			IHoliday repeat = new HolidayAE(7,7,1977,"new title");
			test.insert(repeat);
			test.remove(holidays[77]);
			if(test.get("1977-07-07").size()!=1) {
				fail("size should be 1");
			}
			if(!test.get("1977-07-07").get(0).getName().equals("new title")) {
				fail("title should be new title");
			}
			test1.insert(holidays[10]);
			test1.insert(holidays[15]);
			test1.insert(holidays[5]);
			//.println(test1.toLevelOrderString());
			test1.remove(holidays[5]);
			//.println(test1.toLevelOrderString());
			if(test1.get(RedBlackCalendar.holiDate(holidays[5])).size()!=0) {
				fail("didn't remove node with no children");
			}
			//.println(test1.toLevelOrderString());
			test1.insert(holidays[5]);
			//.println(test1.toLevelOrderString());
			test1.insert(holidays[2]);
			//.println(test1.toLevelOrderString());
			test1.remove(holidays[5]);
			//.println(test1.toLevelOrderString());
			if(test1.get(RedBlackCalendar.holiDate(holidays[5])).size()!=0) {
				fail("didn't remove node with left child");
			}
			test1.remove(holidays[2]);
			//.println(test1.toLevelOrderString());
			test1.insert(holidays[5]);
			test1.insert(holidays[7]);
			//.println(test1.toLevelOrderString());
			test1.remove(holidays[5]);
			//.println(test1.toLevelOrderString());
			if(test1.get(RedBlackCalendar.holiDate(holidays[5])).size()!=0) {
				fail("didn't remove node with right child");
			}
			test1.remove(holidays[7]);
			//.println(test1.toLevelOrderString());
			test1.insert(holidays[5]);
			test1.insert(holidays[7]);
			test1.insert(holidays[2]);
			//.println(test1.toLevelOrderString());
			test1.remove(holidays[5]);
			//.println(test1.toLevelOrderString());
			if(test1.get(RedBlackCalendar.holiDate(holidays[5])).size()!=0) {
				fail("didn't remove node with two children");
			}
			for(int i = 4; i< 15; i++) {
				test.remove(holidays[i]);
				if(test.get(RedBlackCalendar.holiDate(holidays[i])).size()!=0) {
					fail("should be deleted: "+i);
				}
			}
			for(int i = 15; i<20; i++) {
				if(test.get(RedBlackCalendar.holiDate(holidays[i])).size()!=1) {
					fail("shouldn't be deleted: "+i);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			fail("unexpected exception: "+e.getMessage());
		}
		
		assertTrue(true);
	}

}
