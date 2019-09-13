package nanithing.web.jaxrs;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nanithing.model.Game;

/**
 * The Game resource.
 */
@Path("/games")
public interface GameResource {
	@GET
	@Path("/{id}")
	@Produces(APPLICATION_JSON)
	CompletionStage<Game> getById(
			@PathParam("id")
			String id
	);

	@POST
	@Produces(APPLICATION_JSON)
	CompletionStage<Game> create(
			Game game
	);
}
