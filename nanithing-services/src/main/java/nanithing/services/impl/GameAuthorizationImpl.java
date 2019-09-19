package nanithing.services.impl;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameAuthorizationImpl implements GameAuthorization {
	@Override
	public void requireNoActiveGamesForCurrentUser() {

	}
}
