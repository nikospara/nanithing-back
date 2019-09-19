package nanithing.dao.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import nanithing.dao.GameDao;
import nanithing.model.Game;
import nanithing.types.entity.EntityDoesNotExistException;

/**
 * The implementation of the {@link GameDao} with JPA.
 */
@ApplicationScoped
public class GameDaoImpl implements GameDao {

	private EntityManager em;

	/**
	 * Default constructor for the frameworks.
	 */
	GameDaoImpl() {
		// NOOP
	}

	/**
	 * Dependency injection constructor.
	 *
	 * @param em The entity manager
	 */
	@Inject
	public GameDaoImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Game findById(String id) {
		Game game = em.find(GameEntity.class, id);
		if( game == null ) {
			throw new EntityDoesNotExistException();
		}
		return game;
	}

	@Override
	public Game create(Game game) {
		GameEntity ge = new GameEntity();
		ge.setId(game.getId());
		ge.setNumberOfPlayers(game.getNumberOfPlayers());
		ge.setNumberOfRounds(game.getNumberOfRounds());
		ge.setRoundTime(game.getRoundTime());
		ge.setState(game.getState());
		ge.setVisibility(game.getVisibility());
		em.persist(ge);
		return ge;
	}
}
