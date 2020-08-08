package coe528.project;

/**
 * The Class DefineException.
 * An exception that is thrown when problems with
 * file access occur.
 */
public class DefineException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg the message to be shown with an explanation 
	 *            of the exception.
	 */
	public DefineException(String msg) {
		super(msg);
	}

}
