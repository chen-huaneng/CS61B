public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private final Node<T> sentinel;

    private static class Node<T> {
        private T item;
        private Node<T> prev;
        private Node<T> next;

        Node(T item) {
            this.item = item;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node<T> temp = sentinel.next;

        sentinel.next = new Node<>(item);
        sentinel.next.prev = sentinel;
        sentinel.next.next = temp;
        temp.prev = sentinel.next;

        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> temp = sentinel.prev;

        sentinel.prev = new Node<>(item);
        sentinel.prev.next = sentinel;
        sentinel.prev.prev = temp;
        temp.next = sentinel.prev;

        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> temp = sentinel;
        while (temp.next != sentinel) {
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }

        var temp = sentinel.next.next;
        T item = sentinel.next.item;
        sentinel.next = temp;
        temp.prev = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }

        var temp = sentinel.prev.prev;
        T item = sentinel.prev.item;
        sentinel.prev = temp;
        temp.next = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (size <= 0 || index > size || index < 0) {
            return null;
        }
        var temp = sentinel;
        for (int i = 0; i <= index; ++i) {
            temp = temp.next;
        }
        return temp.item;
    }

    /**
     * Same as get, but recursive.
     *
     * @param index the position of item
     * @return the item in the index
     */
    public T getRecursive(int index) {
        if (size <= 0 || index > size || index < 0) {
            return null;
        }
        return getNode(index).item;
    }

    private Node<T> getNode(int index) {
        if (index == 0) {
            return sentinel.next;
        }
        return getNode(index - 1).next;
    }
}
