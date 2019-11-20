package nanithing.web.mappers;

import nanithing.model.ImmutableGame;
import nanithing.web.jaxrs.GameDTO;
import org.mapstruct.Mapper;

/**
 * @author Leon
 */
@Mapper(componentModel="cdi")
public interface GameMapper {

	GameDTO mapDomainToDTO(ImmutableGame game);

	ImmutableGame mapDtoToDomain(GameDTO gameDto);
}
