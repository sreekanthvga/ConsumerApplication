package falcon.consumer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisPersistHandler {

	private JedisPool pool;
	public RedisPersistHandler(JedisPool pool) {
		this.pool = pool;
	}

	public void onMessage(String channel, String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(message);
			try (Jedis jedis = pool.getResource()) {
				
				System.out.println("Redis store message " +  message);
				jedis.set(rootNode.path("UNIQUE_ID").asText(), message);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Failed to persist message: " + message, ex);
		}
	};
	
};