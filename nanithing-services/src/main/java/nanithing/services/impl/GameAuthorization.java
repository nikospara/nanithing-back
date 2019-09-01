package nanithing.services.impl;

/**
 * Authorizer for the Game entity.
 */
public interface GameAuthorization {

	/**
	 * Require that the current user has no active games.
	 * 
	 * @throws nanithing.types.authorization.ConflictingStateException If the requirement is not met
	 */
	void requireNoActiveGamesForCurrentUser();
}
