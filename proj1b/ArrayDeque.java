public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private final int lowSize = 16;
    private int nextFirst;
    private int nextLast;
    private int sizeOfArray = 8;
    private final int factor = 4;
    private T[] items = (T[]) new Object[sizeOfArray];
    public ArrayDeque() {
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T item) {
        if (minusOne(nextFirst) == nextLast) {
            resize(sizeOfArray * factor);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    private int minusOne(int current) {
        return (current - 1 + sizeOfArray) % sizeOfArray;
    }

    @Override
    public void addLast(T item) {
        if (nextLast == minusOne(nextFirst)) {
            resize(sizeOfArray * factor);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size += 1;
    }

    private int addOne(int current) {
        return (current + 1) % sizeOfArray;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int pos = addOne(nextFirst);
        for (int i = 0; i < size; ++i) {
            System.out.print(items[pos] + " ");
            pos = addOne(pos);
        }
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        if (sizeOfArray >= lowSize && (float) size / sizeOfArray <= 0.25) {
            resize(sizeOfArray / (factor / 2));
        }
        nextFirst = addOne(nextFirst);
        size -= 1;
        return items[nextFirst];
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        if (sizeOfArray >= lowSize && (float) size / sizeOfArray <= 0.25) {
            resize(sizeOfArray / (factor / 2));
        }
        nextLast = minusOne(nextLast);
        size -= 1;
        return items[nextLast];
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            return null;
        }
        int pos = addOne(nextFirst);
        pos = (pos + index) % sizeOfArray;
        return items[pos];
    }

    /** Resize the array if the size of array is over the total length.
     * Or resize the array if the usage factor is lower than 25%
     * and the total length of array is less than 16.*/
    private void resize(int newLength) {
        T[] temp = (T[]) new Object[newLength];
        int pos = addOne(nextFirst);
        for (int i = 1; i <= size; ++i) {
            temp[i] = items[pos];
            pos = addOne(pos);
        }
        nextFirst = 0;
        nextLast = size + 1;
        sizeOfArray = newLength;
        items = temp;
    }
}
