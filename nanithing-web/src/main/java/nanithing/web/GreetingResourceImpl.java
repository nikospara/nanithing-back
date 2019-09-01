package nanithing.web;

import static nanithing.model.GameState.BUILDING;
import static nanithing.model.GameVisibility.PUBLIC;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;

import nanithing.dto.GameDTO;
import nanithing.dto.GameMapper;

import nanithing.model.Game;
import nanithing.model.ImmutableGame;

/**
 * Implementation of the {@link GreetingResource}.
 */
public class GreetingResourceImpl implements GreetingResource {

	@Inject
	private GameMapper gameMapper;

	@Override
	public CompletionStage<Game> hello() {
		return CompletableFuture.completedStage(ImmutableGame.builder()
				.id(UUID.randomUUID().toString())
				.numberOfPlayers(2)
				.numberOfRounds(5)
				.roundTime(60)
				.state(BUILDING)
				.visibility(PUBLIC)
				.build()
		);
	}

	@Override
	public CompletionStage<GameDTO> helloWithDTO() {
		final ImmutableGame game = ImmutableGame.builder()
				.id(UUID.randomUUID().toString())
				.numberOfPlayers(2)
				.numberOfRounds(5)
				.roundTime(60)
				.state(BUILDING)
				.visibility(PUBLIC)
				.build();
		return CompletableFuture.completedStage(gameMapper.mapDomainToDTO(game));
	}
}
