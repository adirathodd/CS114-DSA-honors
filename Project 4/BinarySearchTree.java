import java.util.*;
public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E>{
    public boolean search(E data){
        return searchRec(root, data);
    }
    private boolean searchRec(Node<E>curr, E data){
        if (curr == null){
            return false;
        }
        if (curr.data.compareTo(data) == 0){
            return true;
        }
        else if(curr.data.compareTo(data) > 0){
            return searchRec(curr.left, data);
        }
        else if (curr.data.compareTo(data) < 0){
            return searchRec(curr.right, data);
        }
        return false;
    }

    public void insert(E data){
        root = insertRec(root, new Node<E>(data));
    }
    private Node<E> insertRec(Node<E> curr, Node<E> data){
        if (curr == null){
            curr = data;
        }
        else if (curr.data.compareTo(data.data) > 0){
            curr.left = insertRec(curr.left, data);
        }
        else {
            curr.right = insertRec(curr.right, data);
        }
        return curr;
    }

    public void remove(E data){
        if (search(data)){
            root = removeRec(root, data);
        }
    }

    private Node<E> removeRec(Node<E> curr, E data){
        if (curr == null){
            return curr;
        }
        else if (curr.data.compareTo(data) > 0){
            curr.left = removeRec(curr.left, data);
        }
        else if (curr.data.compareTo(data) < 0){
            curr.right = removeRec(curr.right, data);
        }
        else {
            //No child
            if (curr.left == null && curr.right == null){
                return null;
            }
            //Only right child
            else if (curr.left == null){
                return curr.right;
            }
            //Only left child
            else if (curr.right == null){
                return curr.left;
            }
            //Both right and left
            curr.data = minRight(curr.right);
            curr.right = removeRec(curr.right, curr.data);
        }
        return curr;
    }

    private E minRight(Node<E> curr){
        E min = curr.data;
        while (curr.left != null){
            min = curr.left.data;
            curr = curr.left;
        }
        return min;
    }

    public Iterator<E> iterator()
    {
        vector = new Vector<E>();
        traverse(root);
        return vector.iterator();
    }
   
    private Vector<E> vector;
    
    private void traverse(Node<E> curr)
    {
        if(curr != null)
        {
            traverse(curr.left);
            vector.add(curr.data);
            traverse(curr.right);
        }
    }
}
