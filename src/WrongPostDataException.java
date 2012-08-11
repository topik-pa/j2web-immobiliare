
public class WrongPostDataException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorMessage;

	public WrongPostDataException(String errorMessage)
	  {
	    super();             // call superclass constructor
	    this.errorMessage=errorMessage;
	  }
	
	public WrongPostDataException()
	  {
	    super();             // call superclass constructor
	    errorMessage="unknown";
	  }
	
	public String getError()
	  {
	    return errorMessage;
	  }
    
}
