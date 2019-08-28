package nanithing.model;

/**
 * The state of a game.
 */
public enum GameState {
	/** Gathering configuration data to create a game; probably not yet persisted. */
	BUILDING,
	
	/** The game is created and probably persisted, and is waiting for players to join. */
	OPEN,
	
	/** The game is active and under way. */
	STARTED,
	
	/** The game is finished. */
	FINISHED
}
