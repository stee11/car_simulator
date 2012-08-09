/**Steven Kolln
Project 3
CSC 230 02
4/13/11
Queue*/

package javafoundations;

public interface Queue<T>
{
   //  Adds the specified element to the rear of the queue.
   public void enqueue (T element);

   //  Removes and returns the element at the front of the queue.
   public T dequeue();

   //  Returns a reference to the element at the front of the queue
   //  without removing it.
   public T first();

   //  Returns true if the queue contains no elements and false
   //  otherwise.
   public boolean isEmpty();

   //  Returns the number of elements in the queue.
   public int size();

   //  Returns a string representation of the queue.
   public String toString();
}
