package nanithing.web.jaxrs;

import nanithing.model.GameState;
import nanithing.model.GameVisibility;

/**
 * @author Leon
 */
public class GameDTO {

	private String id;
	private GameState state;
	private GameVisibility visibility;
	private int numberOfPlayers;
	private int numberOfRounds;
	private int roundTime;

	public GameDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public GameVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(GameVisibility visibility) {
		this.visibility = visibility;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getNumberOfRounds() {
		return numberOfRounds;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public int getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(int roundTime) {
		this.roundTime = roundTime;
	}
}
