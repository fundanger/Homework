package Homework;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  
public void add(int index, E obj)
  { 
	  listIterator(index).add(obj);
   }
  public void addFirst(E obj) {
	  head = new Node<E>(obj);
	  size++;	  
  }
  public void addLast(E obj) { 
	  Node<E> temp = new Node<E> (obj);
	  temp.prev = tail;
	  tail.next = temp;
	  temp.next = null;	  
  }
  
  public void clear() {
	  head = null;
	  tail = null;
	  size = 0;
  }
  public E get(int index) 
  { 	
	  if (index < 0 || index > size) {
		  throw new IndexOutOfBoundsException();
	  } else {
	  ListIter iter = new ListIter(index); 
      	return iter.next();
	  }
  }  
  public E getFirst() { return head.data;  }
  public E getLast() { return tail.data;  }

  public int size() {  return size;  }

  public E remove(int index)
  {     E returnValue = null;
        ListIter iter = new ListIter(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        } else {  
        	throw new IndexOutOfBoundsException();  
        	}
        return returnValue;
  }

  public Iterator<E> iterator() { return new ListIter(0);  }
  public ListIterator<E> listIterator() { return new ListIter(0);  }
  public ListIterator<E> listIterator(int index){return new ListIter(index);}
  public ListIterator<E> listIterator(ListIterator<E> iter)
  {     return new ListIter( (ListIter) iter);  }

  // Inner Classes
  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        {   data = dataItem;   }
  }  // end class Node

  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 

    public ListIter(int i)  // constructor for ListIter class
    {   if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
        lastItemReturned = null;
 
        if (i == size)     // Special case of last item
        {     index = size;     nextItem = null;      }
        else          // start at the beginning
        {   nextItem = head;
            for (index = 0; index < i; index++)  nextItem = nextItem.next;   
        }// end else
    }  // end constructor

    public ListIter(ListIter other)
    {   nextItem = other.nextItem;
        index = other.index;    }

    public boolean isEmpty() {
    	return size() == 0;
    }
    public boolean hasNext() {  
    	return nextItem != null;
    	}
    public boolean hasPrevious() { 
    	if(size == 0) {
    		return false;
    	}
    	return (nextItem == null && size != 0) || nextItem.prev != null;
    	}
    public int previousIndex() {  return (index - 1);    }
    public int nextIndex() {  return (index);    } 
    public void set(E obj)  {
    	if (lastItemReturned != null)
    	{
    		lastItemReturned.data = obj;
    	} else 
    	{
    		throw new IllegalStateException();
    	}
    }  // not implemented

    public void remove(){
    	if(lastItemReturned != null) {
    	
    	System.out.println(lastItemReturned.data);
    	if (lastItemReturned == head) {
    		head = nextItem;
    		head.prev = null;
    	}
    	else if(lastItemReturned == tail) {
    		lastItemReturned.prev.next = tail;
    		tail = lastItemReturned.prev;
    		tail.next = null;
    		
    	}
    	else {
    		lastItemReturned.next.prev = lastItemReturned.prev;
    		lastItemReturned.prev.next = lastItemReturned.next;
    	}
    	} else {
    		throw new IllegalStateException();
    	}
    	size--;
    	index--;
    }

    public E next()
    {  if (!hasNext()) {
    	throw new NoSuchElementException();
    }
    lastItemReturned = nextItem;
    nextItem = nextItem.next;
    index++;
        return lastItemReturned.data;
    }

    public E previous() 
    { 
    	if(!hasPrevious()) {
    		throw new NoSuchElementException();
    	}
    	if (nextItem == null) {
    		nextItem = tail;
    	} else {
    		nextItem = nextItem.prev;
    	}
    	lastItemReturned = nextItem;
    	index--;
    	return lastItemReturned.data; // Fill Here 
    }

    public void add(E obj) {
    	if (head == null) 
        {
            head = new Node<E>(obj);
            tail = head;
        } 
        else if (nextItem == head)
        { 
            Node<E> newNode = new Node<E>(obj);
            newNode.next = nextItem; 	
            nextItem.prev = newNode; 
            head = newNode;
        } 
        else if (nextItem == null)
        {
            Node<E> newNode = new Node<E>(obj);
            tail.next = newNode; 
            newNode.prev = tail; 
            tail = newNode; 
        }
        else 
        {
            Node<E> newNode = new Node<E>(obj);
            newNode.prev = nextItem.prev; 
            nextItem.prev.next = newNode; 
            newNode.next = nextItem;
            nextItem.prev = newNode; 
        }
        size++;
        index++;
        lastItemReturned = null;
    // Fill Here
    }

    
  }// end of inner class ListIter
}// end of class DoubleLinkedList
