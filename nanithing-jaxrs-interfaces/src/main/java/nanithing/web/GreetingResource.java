package nanithing.web;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nanithing.model.Game;

@Path("/hello")
public interface GreetingResource {

	@GET
	@Produces(APPLICATION_JSON)
	public CompletionStage<Game> hello();
}
