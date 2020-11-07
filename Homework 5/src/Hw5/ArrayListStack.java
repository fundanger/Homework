package Hw5;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> implements StackInterface<E> {

	@SuppressWarnings("unused")
	private ArrayList<E> aList;
	
	public ArrayListStack()
	{
		aList = new ArrayList<E>();
	}
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return aList.isEmpty();
	}

	@Override
	public E peek() {
		if(aList.size() == 0)
		{
			throw new EmptyStackException();
		} else {
			return aList.get(aList.size() - 1);
		}
	}

	@Override
	public E pop() {
		if(aList.isEmpty()) {
			throw new EmptyStackException();
		} else {
			E returnObj = aList.get(aList.size() - 1);
			aList.remove(aList.size()-1);
			return returnObj;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public E push(E obj) {
		aList.add(obj);
		return obj;
	}
	
	public int size() {
		return aList.size();
	}
	}
	

