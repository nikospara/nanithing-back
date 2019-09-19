package nanithing.dao;

import nanithing.model.Game;

/**
 * DAO for the Game entity.
 */
public interface GameDao {

	/**
	 Retrieve a game by id.
	 *
	 * @param id The id of the game
	 * @return The game with the given id, or {@code null} if it doesn't exist
	 * @throws nanithing.types.entity.EntityDoesNotExistException If the game with the requested id does not exist
	 */
	Game findById(String id);

	/**
	 * Create a new {@code Game} in the persistent store.
	 *
	 * @param game The game to create (the id must be set)
	 * @return The created game
	 */
	Game create(Game game);
}
