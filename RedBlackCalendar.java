// --== CS400 File Header Information ==--
// Name: Charles Jungwirth
// Email: crjungwirth@wisc.edu
// Team: DQ
// TA: Ilay Raz
// Lecturer: Florian Heimerl
// Notes to Grader: I messed up the adjust after insert,
//and remove doesn't adjust RBT properties
//Red black calendar alone should work
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class RedBlackCalendar extends RedBlackTree<ComparableList<IHoliday>> implements IRedBlackCalendar  {


	/**
	 * add one holiday
	 */
	@Override
	public void insert(IHoliday holiday) {
		if(holiday == null) {
			return;//just don't add null elements please
		}
		List<IHoliday> already = get(holiDate(holiday));
		if(already.size()==0) {
			ComparableList<IHoliday> toAdd = new ComparableList<IHoliday>();
			toAdd.add(holiday);
			super.insert(toAdd);
		}else {
			already.add(holiday);
			size++;
		}
		
	}


	/**
	 * get all holidays on date
	 */
	@Override
	public List<IHoliday> get(String date) {
		Node<ComparableList<IHoliday>> next = root;
		while(next !=null) {
			int compare = date.compareTo(holiDate(next.data.get(0)));
			if(compare ==0) {
				return next.data;
			}else if(compare<0) {//date is less than
				next = next.leftChild;
			}else {
				next = next.rightChild;
			}
		}
		return new ComparableList<IHoliday>();
	}
	
	/**
	 * remove upto one holiday on same date and with same name
	 * if holiday not found, does nothing
	 */
	@Override
	public void remove(IHoliday holiday) {
		Node<ComparableList<IHoliday>> found = getNode(holiDate(holiday));
		
		if(found == null)
			return;
		
		for(int i=0; i< found.data.size();i++) {
			if(found.data.get(i).getName().equals(holiday.getName())) {
				if(found.data.size()==1) {
					super.removeNode(found);//node is in tree
					return;
				}
				found.data.remove(i);
				size--;
				return;
			}
		}
	}
	/**
	 * usable to optimize insert if I feel like it
	 * and used in remove
	 */
	public Node<ComparableList<IHoliday>> getNode(String date) {
		Node<ComparableList<IHoliday>> next = root;
		Node<ComparableList<IHoliday>> prev = root;
		while(next !=null) {
			int compare = date.compareTo(holiDate(next.data.get(0)));
			if(compare ==0) {
				return next;
			}else if(compare<0) {//date is less than
				prev= next;
				next = next.leftChild;
				
			}else {
				prev = next;
				next = next.rightChild;
			}
		}
		return prev;
	}
	/**
	 * ymdText with IHoliday input
	 * @param holiday
	 * @return date in YYY-MM-DD form
	 */
	public static String holiDate(IHoliday holiday) {
		return ymdText(holiday.getYear(),holiday.getMonth(),holiday.getDay());
	}
	
	
	/**
	 * iterator to go through i holidays
	 * @return iterator with IHolidays
	 */
	public Iterator<IHoliday> IteratorHol(){
		return new Iterator<IHoliday>(){
			private Iterator<ComparableList<IHoliday>> listIt = iterator();
			private int index =-1;
			private ComparableList<IHoliday> current;
			public IHoliday next() {
				if(index == -1||index>=current.size()) {
					current = listIt.next();
					index = 0;
				}
				IHoliday outP = current.get(index);
				index++;
				return outP;
				
			}
			public boolean hasNext() {
				try {
					return(listIt.hasNext()||index<current.size());
				}catch(Exception e){
					return false;
				}
			}
			
		};
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return text in YYYY-MM-DD form
	 */
	public static String ymdText(int year, int month, int day) {
		String ytext = ""+year;
		String mtext = ""+month;
		String dtext = ""+day;
		while(ytext.length()<4) {
			ytext = "0"+ytext;
		}
		while(mtext.length()<2) {
			mtext = "0"+mtext;
		}
		while(dtext.length()<2) {
			dtext = "0"+dtext;
		}
		return ytext+"-"+mtext+"-"+dtext;
	}
	

	/**
	 * return a sorted(by date, then by order added) list of all holidays
	 * from [startDate, endDate]
	 * @param startDate
	 * @param endDate
	 * @param node
	 * @return
	 */
	@Override
	public List<IHoliday> get(String startDate, String endDate) {
		
		return getHelp(startDate,endDate,root);
	}
	
	/**
	 * get left, self right, while checking if they are in bounds
	 * helper method
	 * @param startDate
	 * @param endDate
	 * @param node
	 * @return
	 */
	private List<IHoliday> getHelp(String startDate, String endDate,
			Node<ComparableList<IHoliday>> node){
		if(node == null) {
			return new ComparableList<IHoliday>();
		}
		int compMin = holiDate(node.data.get(0)).compareTo(startDate);
		int compMax = holiDate(node.data.get(0)).compareTo(endDate);
		List<IHoliday> outP = new ComparableList<IHoliday>();
		if(compMin>0) {//add left tree
			outP.addAll(getHelp(startDate,endDate,node.leftChild));
		}
		if(compMin>=0 &&compMax<=0) {
			outP.addAll(node.data);
		}
		if(compMax<0) {//still room till max
			outP.addAll(getHelp(startDate,endDate,node.rightChild));
		}
		return outP;
		
		
	}

	/**
	 * returns the soonest holiday after or on todays date
	 */
	@Override
	public List<IHoliday> getNextHolidays() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return getNextHolidays(format.format(LocalDateTime.now()));
	}
	
	/**
	 * return the next holiday after or on date
	 * @param date
	 * @return
	 */
	public List<IHoliday> getNextHolidays(String date) {
		
		
		return getNextHelp(date,root,null);
	}
	
	/**
	 * 
	 * helper for getting next holiday, works recursively
	 */
	private List<IHoliday> getNextHelp(String date,Node<ComparableList<IHoliday>> guess,
			Node<ComparableList<IHoliday>> max){
		if(guess == null) {
			if(max != null)
				return max.data;
			return new ComparableList<IHoliday>();//no results
		}
		int compare = date.compareTo(holiDate(guess.data.get(0)));
		if(compare ==0) {
			return guess.data;
		}
		if(compare<0) {//this is new max (date must be now or left subtree)
			return getNextHelp(date,guess.leftChild,guess);
		}
		//guess is before date
		return getNextHelp(date,guess.rightChild,max);
		
	}

	

}
