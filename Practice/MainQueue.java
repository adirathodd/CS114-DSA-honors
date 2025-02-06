
import java.util.Iterator;
public class MainQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = Math.random() < 0.5 ? new AQueue<Integer>() : new RQueue<Integer>();
        int num = args.length == 1 ? Integer.parseInt(args[0]) : 11;
        long start, stop;
        System.out.println(queue);
        System.out.println("enqueue");
        start = System.nanoTime();
        for (int i = 0; i < num; ++i) {
            queue.enqueue(i);
            System.out.print(i + " => ");
            for (Integer k : queue) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        stop = System.nanoTime();
        System.out.println(stop-start);
        Integer j;
        System.out.println("dequeue");
        start = System.nanoTime();
        while ((j = queue.dequeue()) != null) {
            System.out.print(j + " => ");
            for (Integer k : queue) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        stop = System.nanoTime();
        System.out.println(stop-start);
    }
}
interface Queue<E> extends Iterable<E> {
    E dequeue();
    void enqueue(E data);
}
class AQueue<E> implements Queue<E> {
    public Iterator<E> iterator() {
        return new QueueIterator<E>();
    }

    private class QueueIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return curr < tail;
        }
        public E next() {
            return (E)queue[curr++];
        }
        private int curr = head;
    }
    public E dequeue() {
        if (queue.length > 10 && (queue.length + tail - head) %
        queue.length <= queue.length / 3) {
            shrink();
        }
        E temp = null;
        if (head != tail) {
            temp = (E)queue[head];
            head = (head + 1) % queue.length;
        }
        return temp;
    }
    public void enqueue(E data) {
        if (head == (tail + 1) % queue.length) {
            grow();
        }
        queue[tail] = data;
        tail = (tail + 1) % queue.length;
    }
    private void grow() {
        Object[] temp = new Object[queue.length * 2];
        for (int i = 0; i < queue.length; ++i) {
            temp[i] = queue[(head + i) % queue.length];
        }
        head = 0;
        tail = queue.length - 1;
        queue = temp;
    }

    private void shrink() {
        Object[] temp = new Object[queue.length / 2];
        for (int i = 0; i < temp.length; ++i) {
            temp[i] = queue[(head + i) % queue.length];
        }
        head = 0;
        tail = queue.length / 3;
        queue = temp;
    }
    private Object[] queue = new Object[10];
    private int head;
    private int tail;
}
class RQueue<E> implements Queue<E> {
    private class Node<T> {
        private Node(T data) {
            this.data = data;
        }
        private T data;
        private Node<T> next;
    }
    public E dequeue() {
        E temp = null;
        if (head != null) {
            temp = head.data;
        if (head == tail) {
            head = tail = null;
        }
        else {
            head = head.next;
        }
        }
        return temp;
    }
    public void enqueue(E data) {
        Node<E> temp = new Node<E>(data);
        if (head == null) {
            head = tail = temp;
        }
        else {
            tail.next = temp;
            tail = tail.next;
        }
    }
    public Iterator<E> iterator() {
    return new Iterator<E>() {
        public boolean hasNext() {
            return curr != null;
        }
        public E next() {
            E temp = curr.data;
            curr = curr.next;
            return temp;
        }
        private Node<E> curr = head;
    };
    }
    private Node<E> head;
    private Node<E> tail;
}
