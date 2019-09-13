package nanithing.web.jaxrs.impl;

import static nanithing.model.GameState.BUILDING;
import static nanithing.model.GameVisibility.PUBLIC;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import nanithing.model.Game;
import nanithing.model.ImmutableGame;
import nanithing.services.GameService;
import nanithing.web.jaxrs.GameResource;

/**
 * Implementation of the Game resource.
 */
@RequestScoped
public class GameResourceImpl implements GameResource {

	@Inject
	private GameService gameService;

	@Override
	public CompletionStage<Game> getById(String id) {
		Game game = gameService.findById(id);
		return CompletableFuture.completedStage(game);
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
