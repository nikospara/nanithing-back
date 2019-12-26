package nanithing.web.jaxrs;


import io.quarkus.security.identity.SecurityIdentity;
import javax.annotation.security.RolesAllowed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public interface UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User user();

	public class User {

		private final String userName;

		public User(SecurityIdentity identity) {
			this.userName = identity.getPrincipal().getName();
		}

		public String getUserName() {
			return userName;
		}
	}
}
