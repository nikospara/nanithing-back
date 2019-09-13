package nanithing.web.jaxrs.impl;

import static nanithing.model.GameState.BUILDING;
import static nanithing.model.GameVisibility.PUBLIC;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.RequestScoped;

import nanithing.model.Game;
import nanithing.model.ImmutableGame;
import nanithing.web.jaxrs.GameResource;

/**
 * Implementation of the Game resource.
 */
@RequestScoped
public class GameResourceImpl implements GameResource {

	@Override
	public CompletionStage<Game> getById(String id) {
		// TODO
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
	public CompletionStage<Game> create(Game game) {
		// TODO
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
