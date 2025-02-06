import java.util.Iterator;

public class testStack {
    public static void main(String[] args){
        Stack<Integer> aStack = new AStack<Integer>();
        Stack<Integer> rStack = new RStack<Integer>();
        System.out.println(aStack);
        System.out.println("push");
        for (int i = 0; i < 11; ++i) {
            aStack.push(i);
            System.out.print(i + " => ");
            for (Integer k : aStack) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        Integer j;
        System.out.println("pop");
        while ((j = aStack.pop()) != null) {
            System.out.print(j + " => ");
            for (Integer k : aStack) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        System.out.println(rStack);
        System.out.println("push");
        for (int i = 0; i < 11; ++i) {
            rStack.push(i);
            System.out.print(i + " => ");
            for (Integer k : rStack) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        Integer f;
        System.out.println("pop");
        while ((f = rStack.pop()) != null) {
            System.out.print(f + " => ");
            for (Integer k : rStack) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }
}

interface Stack<E> extends Iterable<E>{
    E pop();
    void push(E data);
}

class AStack<E> implements Stack<E>{
    private Object[] stack = new Object[10];
    private int top;
    public Iterator<E> iterator(){
        return new StackIterator();
    }

    private class StackIterator<E> implements Iterator<E>{
        private int curr = top;
        public boolean hasNext(){
            return top > 0;
        }
        public E next(){
            return (E)stack[--curr];
        }
    }

    public void push(E data){
        if(top >= stack.length){
            grow();
        }
        stack[top++] = data;
    }

    private void grow(){
        Object[] temp = new Object[stack.length * 2];
        for (int i = 0; i < stack.length; i++){
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public E pop(){
        if (top < stack.length / 3){
            shrink();
        }
        E temp = null;
        if (top > 0){
            temp = (E)stack[--top];
        }
        return temp;
    }

    private void shrink(){
        Object[] temp = new Object[stack.length / 2];
        for (int i = 0; i < stack.length; i++){
            temp[i] = stack[i];
        }
        stack = temp;
    }
}

class RStack<E> implements Stack<E>{
    private class Node<T> {
        private Node(T data) {
            this.data = data;
        }
        private T data;
        private Node<T> next;
    }

    private Node<E> head;

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

    public void push(E data){
        Node<E> temp = new Node(data);
        if(head != null){
            temp.next = head;
            head = temp;
        }
        else{
            head = temp;
        }
    }

    public E pop(){
        E temp = null;
        if (head != null){
            temp = head.data;
            head = head.next;
        }
        return temp;
    }
}