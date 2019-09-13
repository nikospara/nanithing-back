package nanithing.web.jaxrs.impl;

import static nanithing.web.jaxrs.impl.JaxRsApp.APPLICATION_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import nanithing.model.Game;
import nanithing.model.GameState;
import nanithing.model.GameVisibility;
import nanithing.model.ImmutableGame;
import nanithing.services.GameService;
import nanithing.test.CustomMockDispatcherFactory;
import nanithing.web.jaxrs.GameResource;
import org.jboss.resteasy.cdi.ResteasyCdiExtension;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.SingletonResource;
import org.jboss.resteasy.spi.Dispatcher;
import org.jboss.resteasy.spi.metadata.DefaultResourceClass;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddExtensions;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for the {@link GameResourceImpl}.
 */
@EnableAutoWeld
@AddExtensions(ResteasyCdiExtension.class)
@ActivateScopes(RequestScoped.class)
@ExtendWith(MockitoExtension.class)
public class GameResourceImplTest {

	private static final String GAME_ID = UUID.randomUUID().toString();
	private static final int NUM_OF_PLAYERS = 2;
	private static final int NUM_OF_ROUNDS = 5;
	private static final int ROUND_TIME = 120;

	@Produces @Mock
	private GameService gameService;

	@Inject
	private GameResourceImpl sut;

	private Dispatcher dispatcher;

	private MockHttpResponse response;

	@BeforeEach
	void prepareMocks() {
		dispatcher = CustomMockDispatcherFactory.createDispatcher();
		SingletonResource resourceFactory = new SingletonResource(sut, new DefaultResourceClass(GameResource.class, null));
		dispatcher.getRegistry().addResourceFactory(resourceFactory, APPLICATION_PATH);
		response = new MockHttpResponse();
	}

	@Test
	void testGetById() throws Exception {
		MockHttpRequest request = MockHttpRequest.get(APPLICATION_PATH + "/games/" + GAME_ID);
		Game game = ImmutableGame.builder().id(GAME_ID).state(GameState.OPEN).numberOfPlayers(NUM_OF_PLAYERS).numberOfRounds(NUM_OF_ROUNDS).roundTime(ROUND_TIME).visibility(GameVisibility.PRIVATE).build();
		when(gameService.findById(GAME_ID)).thenReturn(game);

		dispatcher.invoke(request, response);

		ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
		verify(gameService).findById(idCaptor.capture());
		assertEquals(GAME_ID, idCaptor.getValue());

		GameAssertions.assertGame(response)
				.assertId(GAME_ID);
	}
}
