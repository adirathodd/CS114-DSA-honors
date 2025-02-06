import java.util.Iterator;

public class testQueue {
    public static void main(String[] args){
        Queue<Integer> aQueue = new AQueue<Integer>();
        Queue<Integer> rQueue = new RQueue<Integer>();
        System.out.println(aQueue);
        System.out.println("push");
        for (int i = 0; i < 11; ++i) {
            aQueue.enqueue(i);
            System.out.print(i + " => ");
            for (Integer k : aQueue) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        Integer j;
        System.out.println("pop");
        while ((j = aQueue.dequeue()) != null) {
            System.out.print(j + " => ");
            for (Integer k : aQueue) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        System.out.println(rQueue);
        System.out.println("push");
        for (int i = 0; i < 11; ++i) {
            rQueue.enqueue(i);
            System.out.print(i + " => ");
            for (Integer k : rQueue) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        Integer f;
        System.out.println("pop");
        while ((f = rQueue.dequeue()) != null) {
            System.out.print(f + " => ");
            for (Integer k : rQueue) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }
}

interface Queue<E> extends Iterable<E>{
    E dequeue();
    void enqueue(E data);
}

class AQueue<E> implements Queue<E>{
    private Object[] queue = new Object[10];
    private int head;
    private int tail;

    public Iterator<E> iterator(){
        return new QueueIterator<E>();
    }

    private class QueueIterator<E> implements Iterator<E>{
        private int curr = head;
        public boolean hasNext(){
            return curr < tail;
        }
        public E next() {
            return (E)queue[curr++];
        }
    }

    public void enqueue(E data){
        if (head == (tail + 1) % queue.length){
            grow();
        }
        queue[tail] = data;
        tail = (tail + 1) % queue.length;

    }

    private void grow(){
        Object[] temp = new Object[queue.length * 2];
        for (int i = 0; i < queue.length; i++){
            temp[i] = queue[(head + i) % queue.length];
        }
        head = 0;
        tail = queue.length - 1;
        queue = temp;
    }

    public E dequeue(){
        E temp = null;
        if ((queue.length + tail - head) % queue.length <= queue.length / 3){
            shrink();
        }
        if (head != tail){
            temp = (E)queue[head];
            head = (head + 1) % queue.length;
        }
        return temp;
    }

    public void shrink(){
        Object[] temp = new Object[queue.length / 2];
        for (int i = 0; i < queue.length; i++){
            temp[i] = queue[(head + i) % queue.length];
        }
        head = 0;
        tail = queue.length / 3;
        queue = temp;
    }
}

class RQueue<E> implements Queue<E> {
    private class Node<T>{
        private T data;
        private Node<T> next;
        public Node(T data){
            this.data = data;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    public Iterator<E> iterator(){
        return new Iterator<E>() {
            private Node<E> curr = head;
            public boolean hasNext(){
                return curr.next != null;
            }
            public E next(){
                E temp = curr.data;
                curr = curr.next;
                return temp;
            }
        };
    }

    public void enqueue(E data){
        Node<E> temp = new Node<E>(data);
        if(head == null){
            head = temp;
        }
        else {
            tail.next = temp;
            tail = temp;
        }
    }

    public E dequeue(){
        E temp = null;
        if (head != null){
            temp = (E)head.data;
            head = head.next;
        }
        return temp;
    }
}