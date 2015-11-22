package falcon.consumer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ConsumerApplication {

    public static final String CHANNEL_NAME = "BROADCAST";
    public static void main(String[] args) throws Exception {

    	final JedisPoolConfig poolConfig = new JedisPoolConfig();
        final JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379, 0);
        final Jedis subscriberJedis = jedisPool.getResource();
        final RedisSubscriber subscriber = new RedisSubscriber(jedisPool);
        
        try {
        	System.out.println("Subscribing to \"Broadcast\"");
            subscriberJedis.subscribe(subscriber, CHANNEL_NAME);
            System.out.println("Subscription Done.");
        } catch (Exception e) {
        	System.out.println("Subscribing failed. => " + e);
        }finally {
        	subscriber.unsubscribe();
            jedisPool.returnResource(subscriberJedis);
        }

    }
};