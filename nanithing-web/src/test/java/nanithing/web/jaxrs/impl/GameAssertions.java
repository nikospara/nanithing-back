package nanithing.web.jaxrs.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.jboss.resteasy.mock.MockHttpResponse;

/**
 * Assertions to test JSON responses of the {@link nanithing.model.Game} type.
 */
class GameAssertions {
	private JsonObject data;

	static GameAssertions assertGame(MockHttpResponse response) throws UnsupportedEncodingException {
		assertEquals(200, response.getStatus());
		JsonReader jsonReader = Json.createReader(new StringReader(response.getContentAsString()));
		JsonObject jobj = jsonReader.readObject();
		assertEquals(6, jobj.size());
		return new GameAssertions(jobj);
	}

	private GameAssertions(JsonObject data) {
		this.data = data;
	}

	GameAssertions assertId(String expected) {
		assertEquals(expected, data.getString("id"));
		return this;
	}
}
