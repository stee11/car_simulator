/**Steven Kolln
Project 3
CSC 230 02
4/13/11
LinkedQueue*/
package javafoundations;
import javafoundations.exceptions.*;

public class LinkedQueue<T> implements Queue<T>
{
   private int count;
   private LinearNode<T> front, rear;

   //-----------------------------------------------------------------
   //  Creates an empty queue.
   //-----------------------------------------------------------------
   public LinkedQueue()
   {
      count = 0;
      front = rear = null;
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the rear of this queue.
   //-----------------------------------------------------------------
   public void enqueue (T element)
   {
      LinearNode<T> node = new LinearNode<T>(element);

      if (count == 0)
         front = node;
      else
         rear.setNext(node);

      rear = node;
      count++;
   }
   /**Checks if there is an element in the queue before excecuting. Then assigns T element to the first element in the queue and sets the element behind front to be the new front*/
	public T dequeue () throws EmptyCollectionException 
	{
		if(count<1)
            throw new EmptyCollectionException("Road is empty.");
        T element=front.getElement();
        front=front.getNext();
		count--;
        return element;
	}
    public T first () throws EmptyCollectionException
	{
		if (count<1)
			throw new EmptyCollectionException("Road is empty.");
		return front.getElement();
	}
    public boolean isEmpty()
	{
		if (count<1)
			return true;
		else
			return false;
	}
    public int size()
	{
		return count;
	}
    public String toString()
	{
		String result ="";// "<Front of the Queue>\n";
        LinearNode current = front;
		while (current != null)
        {
			result += current.getElement() + "\n";
			current = current.getNext();
		}
		return result; //+ "<Rear of the Queue>";
	}
}
