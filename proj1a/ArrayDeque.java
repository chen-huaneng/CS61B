public class ArrayDeque<T> {
    private int size;
    private final int factor = 4;
    private int nextFirst;
    private final int lowsize = 16;
    private int nextLast;
    private int sizeOfArray = 8;
    private T[] items = (T[]) new Object[sizeOfArray];
    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = sizeOfArray - 1;
    }

    public void addFirst(T item) {
        if ((nextFirst + sizeOfArray) % sizeOfArray == nextLast) {
            resize();
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + sizeOfArray) % sizeOfArray;
        size += 1;
    }

    public void addLast(T item) {
        if ((nextFirst + sizeOfArray) % sizeOfArray == nextLast) {
            resize();
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % sizeOfArray;
        size += 1;
    }

    public boolean isEmpty() {
        return (nextFirst + sizeOfArray - 1) % sizeOfArray == nextLast;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst - 1; i < size; i = (i + 1) % sizeOfArray) {
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        nextFirst = (nextFirst + 1) % sizeOfArray;
        size -= 1;
        return items[nextFirst];
    }

    public T removeLast() {
        nextLast = (nextLast - 1 + sizeOfArray) % sizeOfArray;
        size -= 1;
        return items[nextLast];
    }

    public T get(int index) {
        if (index > size || index <= 0) {
            return null;
        }
        return items[index];
    }

    /** Resize the array if the size of array is over the total length
     * or if the usage factor is lower than 25% and the total length of array is less than 16.*/
    private void resize() {
        int newLength;
        if (sizeOfArray >= lowsize && (float) size / sizeOfArray <= 0.25) {
            newLength = sizeOfArray / factor;
        } else if (size >= sizeOfArray) {
            newLength = sizeOfArray * factor;
        } else {
            return;
        }
        T[] temp = (T[]) new Object[newLength];
        for (int i = (nextFirst + 1) % sizeOfArray; i < size; i = (i + 1) % sizeOfArray) {
            temp[i] = items[i];
        }
        sizeOfArray = newLength;
        items = temp;
    }
}
