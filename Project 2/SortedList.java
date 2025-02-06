import java.util.Iterator;

public class SortedList<E extends Comparable<? super E>> extends List<E> {
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
        Node<E> curr = head;
        };
    }

    public void insert(E data) {
        head = insertRec(data, head);
    }

    private Node<E> insertRec(E data, Node<E> curr){
        if (curr == null) {
            curr = new Node<E>(data);
            return curr;
        }
        if (curr.data.compareTo(data) >= 0){
            Node<E> insertN = new Node<E>(data);
            insertN.next = curr;
            return insertN;
        }
        curr.next = insertRec(data, curr.next);
        return curr;
    }
    public void remove(E data){
        head = removeRec(data, head);
    }

    private Node<E> removeRec(E data, Node<E> curr){
        if (curr == null){
            return null;
        }
        if (curr.data.compareTo(data) == 0){
            return curr.next;
        }
        curr.next = removeRec(data, curr.next);
        return curr;
    }
    public E retrieve(int index){
        return retrieveRec(index, 0, head);
    }

    private E retrieveRec(int index, int count, Node<E> curr){
        if (curr == null){
            return null;
        }
        if (index == count){
            return curr.data;
        }
        E out = retrieveRec(index, count + 1, curr.next);
        return out;
    }
    public boolean search(E data){
        return searchRec(data, head);
    }

    private boolean searchRec(E data, Node<E> curr){
        if (curr == null){
            return false;
        }
        if (curr.data.compareTo(data) == 0){
            return true;
        }
        boolean out = searchRec(data, curr.next);
        return out;
    }
}