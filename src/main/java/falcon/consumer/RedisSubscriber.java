package falcon.consumer;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber extends JedisPubSub {
	
	private RedisPersistHandler pHandler;
	
	public RedisSubscriber(JedisPool pool) {
		this.pHandler = new RedisPersistHandler(pool);
	}
	
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("onUnsubscribe");
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("onSubscribe");
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		System.out.println("onPUnsubscribe");
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out.println("onPSubscribe");
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("onPMessage");
	}

	@Override
    public void onMessage(String channel, String message) {
		try {
			pHandler.onMessage(channel, message);
		} catch (Exception ex) {
			System.out.println("Failed to Persist message " + message + " on channel " + channel);
		}
	}
	
};