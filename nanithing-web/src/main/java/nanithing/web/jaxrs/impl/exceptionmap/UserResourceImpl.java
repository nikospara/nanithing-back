package nanithing.web.jaxrs.impl.exceptionmap;

import io.quarkus.security.identity.SecurityIdentity;
import nanithing.web.jaxrs.UserResource;

import javax.inject.Inject;

public class UserResourceImpl implements UserResource {


	@Inject
	SecurityIdentity identity;

	public User user() {
		return new User(identity);
	}

}
