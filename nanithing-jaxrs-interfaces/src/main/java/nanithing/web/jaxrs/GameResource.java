package nanithing.web.jaxrs;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nanithing.model.Game;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

/**
 * The Game resource.
 */
@Path("/games")
@Authenticated
public interface GameResource {
	@GET
	@Path("/{id}")
	@Produces(APPLICATION_JSON)
	@APIResponses(
		@APIResponse(responseCode = "200", description = "Successfully returned a game for the given id")
	)
	CompletionStage<Game> getById(
			@PathParam("id")
			String id
	);

	@POST
	@Produces(APPLICATION_JSON)
	@APIResponses(
			{@APIResponse(responseCode = "200", description = "Successfully created a game"),
			@APIResponse(responseCode = "400", description = "Invalid game parameter")}
	)
	CompletionStage<Game> create(
			Game game
	);
}
