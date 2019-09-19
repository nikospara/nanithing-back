package nanithing.dao.jpa;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import nanithing.dao.jpa.converters.GameStateConverter;
import nanithing.dao.jpa.converters.GameVisibilityConverter;
import nanithing.model.Game;
import nanithing.model.GameState;
import nanithing.model.GameVisibility;

/**
 * The Game Entity.
 */
@Entity
@Table(name = "NAT_GAME")
public class GameEntity implements Game {
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "STATE")
	@Convert(converter = GameStateConverter.class)
	private GameState state;

	@Column(name = "VISIBILITY")
	@Convert(converter = GameVisibilityConverter.class)
	private GameVisibility visibility;

	@Column(name = "NPLAYERS")
	private int numberOfPlayers;

	@Column(name = "NROUNDS")
	private int numberOfRounds;

	@Column(name = "ROUND_TIME")
	private int roundTime;

	public void setId(String id) {
		this.id = id;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public void setVisibility(GameVisibility visibility) {
		this.visibility = visibility;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public void setRoundTime(int roundTime) {
		this.roundTime = roundTime;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public GameState getState() {
		return state;
	}

	@Override
	public GameVisibility getVisibility() {
		return visibility;
	}

	@Override
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	@Override
	public int getNumberOfRounds() {
		return numberOfRounds;
	}

	@Override
	public int getRoundTime() {
		return roundTime;
	}
}
