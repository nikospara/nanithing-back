package nanithing.types.authorization;

/**
 * An exception during authorization indicating that the client does
 * not have access rights to the requested resource or operation.
 * This exception should not be thrown to indicate system problems.
 */
public class NotAuthorizedException extends AppSecurityException {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public NotAuthorizedException() {
	}

	/**
	 * Create the exception based on a message.
	 *
	 * @param message The message
	 */
	public NotAuthorizedException(String message) {
		super(message);
	}
}
