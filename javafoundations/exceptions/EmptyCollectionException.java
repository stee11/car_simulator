/**Steven Kolln
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
