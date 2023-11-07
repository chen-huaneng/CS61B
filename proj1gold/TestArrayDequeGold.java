import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {

    @Test
    public void aSimpleTest() {
        var stud1 = new StudentArrayDeque<Integer>();
        var solution = new ArrayDequeSolution<Integer>();
        int sizeOfCurrentArray = 0;
        String errorMessage = "\n";

        for (int i = 0; i < 10000; ++i) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                errorMessage += "addLast(" + i + ")\n";
                stud1.addLast(i);
                solution.addLast(i);
                ++sizeOfCurrentArray;
            } else if (numberBetweenZeroAndOne < 0.5) {
                errorMessage += "addFirst(" + i + ")\n";
                stud1.addFirst(i);
                solution.addFirst(i);
                ++sizeOfCurrentArray;
            } else if (numberBetweenZeroAndOne < 0.75) {
                if (sizeOfCurrentArray <= 0) {
                    continue;
                }
                errorMessage += "removeFirst()\n";
                assertEquals(errorMessage, solution.removeFirst(), stud1.removeFirst());
                --sizeOfCurrentArray;
            } else {
                if (sizeOfCurrentArray <= 0) {
                    continue;
                }
                errorMessage += "removeLast()\n";
                assertEquals(errorMessage, solution.removeLast(), stud1.removeLast());
                --sizeOfCurrentArray;
            }
        }
    }
}
