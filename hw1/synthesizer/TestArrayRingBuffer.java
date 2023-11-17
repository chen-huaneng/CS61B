package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(10);
        arb.enqueue(20);
        assertEquals(10, arb.peek());
        assertEquals(10, arb.dequeue());
        assertEquals(20, arb.dequeue());
        assertTrue(arb.isEmpty());
        arb.enqueue(30);
        arb.enqueue(40);
        arb.enqueue(50);
        arb.enqueue(60);
        arb.enqueue(70);
        arb.enqueue(80);
        arb.enqueue(90);
        arb.enqueue(10);
        arb.enqueue(20);
        arb.enqueue(11);
        assertTrue(arb.isFull());
        assertEquals(10, arb.capacity());
        assertFalse(arb.isEmpty());
        assertEquals(30, arb.peek());
        assertEquals(30, arb.dequeue());
        assertEquals(40, arb.dequeue());
        assertEquals(50, arb.dequeue());
        assertEquals(60, arb.dequeue());
        assertEquals(70, arb.dequeue());
        assertEquals(80, arb.dequeue());
        assertEquals(90, arb.dequeue());
        assertEquals(10, arb.dequeue());
        assertEquals(20, arb.dequeue());
        assertEquals(11, arb.dequeue());

        int[] array = {30, 40, 50, 60, 70, 80, 90, 10, 20, 11};
        int j = 0;
        Iterator<Integer> seer = arb.iterator();
        while (seer.hasNext()) {
            assertEquals(array[j], (int) seer.next());
            ++j;
        }

        String[] testString = {"String1", "String2", "String3", "String4", "String5", "String6"};
        ArrayRingBuffer<String> test = new ArrayRingBuffer(10);
        test.enqueue("String1");
        test.enqueue("String2");
        test.enqueue("String3");
        test.enqueue("String4");
        test.enqueue("String5");
        test.enqueue("String6");
        int i = 0;
        for (String s : test) {
            assertEquals(testString[i], s);
            ++i;
        }


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
