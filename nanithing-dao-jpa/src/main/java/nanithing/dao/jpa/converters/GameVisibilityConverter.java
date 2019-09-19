package nanithing.dao.jpa.converters;

import java.util.Map;

import nanithing.model.GameVisibility;

public class GameVisibilityConverter extends EnumConverter<GameVisibility> {

	private static final Map<String,GameVisibility> MAP = mapStringToEnum(GameVisibility.class, 2);

	public GameVisibilityConverter() {
		super(2);
	}

	@Override
	public GameVisibility convertToEntityAttribute(String s) {
		return s == null ? null : MAP.get(s);
	}
}
