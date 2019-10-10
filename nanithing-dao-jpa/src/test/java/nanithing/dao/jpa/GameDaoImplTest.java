package nanithing.dao.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;
import javax.persistence.EntityManager;

import nanithing.model.Game;
import nanithing.model.GameState;
import nanithing.model.GameVisibility;
import nanithing.model.ImmutableGame;
import nanithing.test.JpaDaoExtension;
import nanithing.test.LiquibaseExtension;
import nanithing.types.entity.EntityDoesNotExistException;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Tests for the {@link GameDaoImpl}.
 */
@ExtendWith({LiquibaseExtension.class, JpaDaoExtension.class})
@EnabledIfSystemProperty(named = "database-test.active", matches = "true")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameDaoImplTest {

	private static final String GAME_ID = UUID.randomUUID().toString();
	private static final int NUM_OF_PLAYERS = 2;
	private static final int NUM_OF_ROUNDS = 5;
	private static final int ROUND_TIME = 120;

	private EntityManager em;
	private Statistics statistics;
	private GameDaoImpl sut;

	@BeforeEach
	void init(EntityManager em, Statistics statistics) {
		this.em = em;
		this.statistics = statistics;
		sut = new GameDaoImpl(em);
	}

	@AfterEach
	void afterEach() {
		statistics.clear();
	}

	@Test
	@Order(1)
	void testCreate() {
		em.getTransaction().begin();
		Game game = ImmutableGame.builder().id(GAME_ID).state(GameState.OPEN).numberOfPlayers(NUM_OF_PLAYERS).numberOfRounds(NUM_OF_ROUNDS).roundTime(ROUND_TIME).visibility(GameVisibility.PRIVATE).build();
		sut.create(game);
		em.flush();
		em.getTransaction().commit();

		GameEntity ge = em.find(GameEntity.class, GAME_ID);
		assertNotNull(ge);
		assertEquals(GAME_ID, ge.getId());
		assertEquals(GameState.OPEN, ge.getState());
		assertEquals(NUM_OF_PLAYERS, ge.getNumberOfPlayers());
		assertEquals(ROUND_TIME, ge.getRoundTime());
		assertEquals(GameVisibility.PRIVATE, ge.getVisibility());
	}

	@Test
	@Order(2)
	void testFindById() {
		Game game = sut.findById(GAME_ID);
		assertNotNull(game);
		assertEquals(GAME_ID, game.getId());
		try {
			Game shouldBeNull = sut.findById("NON EXISTING ID");
			fail("should have thrown");
		}
		catch( EntityDoesNotExistException expected ) {
			// expected
		}
	}
}
