package nanithing.dao.jpa.converters;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;

/**
 * Convert a Java {@code enum} to a short DB column.
 *
 * @param <E> The converter enum
 */
public abstract class EnumConverter<E extends Enum<E>> implements AttributeConverter<E,String> {

	private int length;

	protected EnumConverter() {
		this(1);
	}

	protected EnumConverter(int length) {
		this.length = length;
	}

	@Override
	public String convertToDatabaseColumn(E e) {
		return e != null ? e.name().substring(0,length) : null;
	}

	protected static <E extends Enum<E>> Map<String,E> mapStringToEnum(Class<E> enumClass, int length) {
		return Arrays.stream(enumClass.getEnumConstants())
				.collect(Collectors.toUnmodifiableMap(e -> e.name().substring(0,length), Function.identity()));
	}
}
