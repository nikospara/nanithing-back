package nanithing.dao.jpa.converters;

import java.util.Map;

import nanithing.model.GameState;

/**
 * Convert the {@link GameState} enum to its first letter when interacting with the DB.
 */
public class GameStateConverter extends EnumConverter<GameState> {

	private static final Map<String,GameState> MAP = mapStringToEnum(GameState.class, 1);

	@Override
	public GameState convertToEntityAttribute(String s) {
		return s == null ? null : MAP.get(s);
	}
}
