
import java.util.List;
/**
 * is a list with a compare to that compares first elements
 *
 * @param <T> type of element in list
 */
public interface IComparableList<T extends Comparable<T>>  extends List<T>, Comparable<IComparableList<T>>{

	
}

