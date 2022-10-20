import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/**
 * 
 * @author charlie jungwirth
 *	A linked list that can use a compare to
 *	that compares the first two elements
 *	returns 0 if exceptions occur
 * @param <T>
 */
public class ComparableList <T extends Comparable<T>>  extends LinkedList<T> implements Comparable<ComparableList<T>>{

	
	public int compareTo(ComparableList<T> arg0) {
		try {
			return this.get(0).compareTo(arg0.get(0));
		}catch( Exception e) {
			return 0;//should never get here
		}
	}

	
	

}
