package nanithing.services.authorization;

/**
 * Helper methods for authorizing the access to services.
 */
public interface Authorization {

	/**
	 * Require a logged-in user.
	 * 
	 * @throws NotAuthenticatedException If the requirement is not met
	 */
	void requireLogin();

	/**
	 * Require that the logged-in user has the given username.
	 *
	 * @param username The username
	 * @throws NotAuthorizedException If the requirement is not met
	 */
	void requireUsername(String username);

	/**
	 * Require that the logged-in user has the given id.
	 *
	 * @param userId The user id
	 * @throws NotAuthorizedException If the requirement is not met
	 */
	void requireUserId(String userId);
}
