/**Steven Kolln
Project 3
CSC 230 02
4/13/11
EmptyCollectionException*/

package javafoundations.exceptions;

public class EmptyCollectionException extends RuntimeException
{
   //------------------------------------------------------------------
   //  Sets up this exception with an appropriate message.
   //------------------------------------------------------------------
   public EmptyCollectionException (String message)
   {
      super (message);
   }
}
