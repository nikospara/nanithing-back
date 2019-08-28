package nanithing.web;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static nanithing.model.GameState.BUILDING;
import static nanithing.model.GameVisibility.PUBLIC;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nanithing.model.Game;
import nanithing.model.ImmutableGame;

@Path("/hello")
public class GreetingResource {

	@GET
	@Produces(APPLICATION_JSON)
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
