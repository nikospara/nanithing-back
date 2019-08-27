package nanithing.model;

import org.immutables.value.Value;

/**
 * Representation of a Name-Animal-Thing game.
 */
@Value.Immutable
public interface Game {

	/**
	 * The game id.
	 * 
	 * @return The game id
	 */
	String getId();
	
	/**
	 * The state of the game.
	 * 
	 * @return The state of the game
	 */
	GameState getState();
	
	/**
	 * The visibility of the game.
	 * 
	 * @return The visibility of the game
	 */
	GameVisibility getVisibility();
	
	/**
	 * The number of players.
	 * 
	 * @return The number of players
	 */
	int getNumberOfPlayers();
	
	/**
	 * The number of rounds, -1 for infinite.
	 * 
	 * @return The number of rounds
	 */
	int getNumberOfRounds();
	
	/**
	 * The time for each round in seconds.
	 * 
	 * @return The time for each round
	 */
	int getRoundTime();
}
