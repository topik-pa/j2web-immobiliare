
public class HttpResponseException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cause;

	public HttpResponseException(String cause)
	  {
	    super();             // call superclass constructor
	    this.cause=cause;
	  }
	
	public HttpResponseException()
	  {
	    super();             // call superclass constructor
	    cause="unknown";
	  }
	
	public String getError()
	  {
	    return cause;
	  }
    
}
