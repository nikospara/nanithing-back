package nanithing.web.jaxrs.impl;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.mock;

import io.quarkus.test.Mock;
import nanithing.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.enterprise.inject.Produces;

/**
 * Tests for the {@link GameResourceImpl}.
 */
@QuarkusTest
//@ExtendWith(MockitoExtension.class)
public class GameResourceImplTest {

	@Produces @Mock
	private GameService gameService;

	@BeforeEach
	void prepareMocks() {
		gameService = mock(GameService.class);
	}

	@Test
	void testGetById() {
		given()
			.when().get("/games/game_id")
			.then()
				.statusCode(200);
	}
}
