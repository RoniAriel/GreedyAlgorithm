import java.util.*;
import java.util.Collection;

public interface Greedy<T> {

    /**
     * A selection function, which chooses the best candidate to be added to the solution
     */
    Iterator<T> selection();

    /**
     * A feasibility function, that is used to determine if a candidate can be used to contribute to a solution
     */
    boolean feasibility(List<T> lst, T element);

    /**
     * An assign function, which assigns a value to a solution, or a partial solution
     */
    void assign(List<T> lst, T element);

    /**
     * A solution function, which will indicate when we have discovered a complete solution
     */
    boolean solution(List<T> lst);

    /**
     * The Greedy Algorithm
     */

    default void BuildTheVertexes() {
    }

    default List<T> greedyAlgorithm() {
        BuildTheVertexes();
        List<T> CanadidatesList = new ArrayList<>();
        Iterator<T> nextValue = selection();
        search:
        while (nextValue.hasNext()) {
            T nextElement = nextValue.next();
            if (feasibility(CanadidatesList, nextElement)) {
                assign(CanadidatesList, nextElement);
            }
            if (solution(CanadidatesList)) {
                break search;
            }
        }
        if (CanadidatesList.size() > 0) {
            return CanadidatesList;
        }
        return null;
    }
}
