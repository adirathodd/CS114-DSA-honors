import java.util.*;
public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> 
{
    public boolean search(E data)
    {
        return search(root, data);
    }
    
    private boolean search(Node<E> curr, E data)
    {
        if(curr == null)
        {
            return false;
        }
        int cmp;
        if((cmp = curr.data.compareTo(data)) == 0)
        {
            return true;
        }
        else if(cmp > 0)
        {
            return search(curr.left, data);
        }
        else if(cmp < 0)
        {
            return search(curr.right, data);
        }
        return false;
    }
    
    public void insert(E data)
    {
        root = insert(root, new Node<E>(data));
    }
    
    private Node<E> insert(Node<E> curr, Node<E> data)
    {
        if(curr == null)
        {
            curr = data;
        }
        else if(data.data.compareTo(curr.data) <= 0)
        {
            curr.left = insert(curr.left, data);
        }
        else
        {
            curr.right = insert(curr.right, data);
        }
        return curr;
    }
    
    public void remove(E data)
    {
        root = remove(root, data);
    }
    
    private Node<E> remove(Node<E> curr, E data)
    {
        if(curr == null)
        {
            return curr;
        }
        if(data.compareTo(curr.data) < 0)
        {
            curr.left = remove(curr.left, data);
        }
        else if(data.compareTo(curr.data) > 0)
        {
            curr.right = remove(curr.right, data);
        }
        else
        {
            if(curr.left == null)
            {
                return curr.right;
            }
            else if(curr.right == null)
            {
                return curr.left;
            }
            curr.data = minVal(curr.right);
            curr.right = remove(curr.right, curr.data);
        }
        return curr;
    }
    
    private E minVal(Node<E> curr)
    {
        E min = curr.data;
        while(curr.left != null)
        {
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