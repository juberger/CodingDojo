package fr.astek.java2d.exception;
public class MediaException extends Exception {
	
	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8802130976745656149L;

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	public MediaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MediaException(Throwable cause) {
		super(cause);
	}
	
	public MediaException(String message) {
		super(message);
	}

	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

}