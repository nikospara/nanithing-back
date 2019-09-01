package nanithing.dto;

import nanithing.model.ImmutableGame;
import org.mapstruct.Mapper;

/**
 * @author Leon
 */
@Mapper
public interface GameMapper {

	GameDTO mapDomainToDTO(ImmutableGame game);

	ImmutableGame mapDtoToDomain(GameDTO gameDto);
}
