package nanithing.services.impl;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import nanithing.dao.GameDao;
import nanithing.model.Game;
import nanithing.model.GameState;
import nanithing.model.ImmutableGame;
import nanithing.services.GameService;
import nanithing.services.impl.authorization.Authorization;

/**
 * Implementation of the {@link GameService}.
 */
@ApplicationScoped
@Transactional
public class GameServiceImpl implements GameService {
	
	private Authorization authorization;
	
	private GameAuthorization gameAuthorization;
	
	private GameDao gameDao;
	
	/**
	 * Default constructor for frameworks.
	 */
	GameServiceImpl() {
		// NOOP
	}

	/**
	 * Constructor for injection.
	 * 
	 * @param authorization The authorization
	 * @param gameAuthorization The Game entity specific authorization
	 * @param gameDao The Game entity DAO
	 */
	@Inject
	public GameServiceImpl(Authorization authorization, GameAuthorization gameAuthorization, GameDao gameDao) {
		this.authorization = authorization;
		this.gameAuthorization = gameAuthorization;
		this.gameDao = gameDao;
	}

	@Override
	public Game findById(String id) {
		return gameDao.findById(id);
	}

	@Override
	public Game create(Game game) {
		authorization.requireLogin();
		gameAuthorization.requireNoActiveGamesForCurrentUser();
		Game gameToCreate = ImmutableGame.builder()
				.from(game)
				.id(game.getId() != null ? game.getId() : UUID.randomUUID().toString())
				.state(GameState.OPEN)
				.build();
		return gameDao.create(gameToCreate);
	}
}
