package nanithing.web;

import static nanithing.model.GameState.BUILDING;
import static nanithing.model.GameVisibility.PUBLIC;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import nanithing.model.Game;
import nanithing.model.ImmutableGame;

/**
 * Implementation of the {@link GreetingResource}.
 */
public class GreetingResourceImpl implements GreetingResource {

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
}
