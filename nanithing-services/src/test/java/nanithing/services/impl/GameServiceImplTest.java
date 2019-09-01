package nanithing.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nanithing.dao.GameDao;
import nanithing.model.Game;
import nanithing.model.GameState;
import nanithing.services.authorization.Authorization;
import nanithing.types.authorization.ConflictingStateException;
import nanithing.types.authorization.NotAuthenticatedException;

/**
 * Tests for the {@link GameServiceImpl}.
 */
@EnableAutoWeld
@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

	@Produces @Mock
	private Authorization authorization;
	
	@Produces @Mock
	private GameAuthorization gameAuthorization;
	
	@Produces @Mock
	private GameDao gameDao;
	
	@Inject
	private GameServiceImpl sut;
	
	@Test
	void testCreateRequiresActiveUser() {
		doThrow(NotAuthenticatedException.class).when(authorization).requireLogin();
		try {
			sut.create(mock(Game.class));
			fail("should throw");
		}
		catch(NotAuthenticatedException expected) {
			// expected
		}
	}
	
	@Test
	void testCreateRequiresNoActiveGames() {
		doThrow(ConflictingStateException.class).when(gameAuthorization).requireNoActiveGamesForCurrentUser();
		try {
			sut.create(mock(Game.class));
			fail("should throw");
		}
		catch(ConflictingStateException expected) {
			// expected
		}
	}
	
	@Test
	void testCreateCreatesAnOpenGame() {
		when(gameDao.create(any(Game.class))).thenAnswer(inv -> inv.getArgument(0));
		Game result = sut.create(mock(Game.class));
		assertNotNull(result);
		ArgumentCaptor<Game> gameArgumentCaptor = ArgumentCaptor.forClass(Game.class);
		verify(gameDao).create(gameArgumentCaptor.capture());
		Game gameToCreate = gameArgumentCaptor.getValue();
		assertNotNull(gameToCreate);
		assertNotNull(gameToCreate.getId());
		assertEquals(GameState.OPEN, gameToCreate.getState());
	}
}
