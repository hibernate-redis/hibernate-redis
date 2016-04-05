/*
 * Copyright 2011-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibernate.test.cache

import org.hibernate.cache.redis.jedis.JedisClient
import org.hibernate.cache.redis.serializer.SnappyRedisSerializer
import org.hibernate.cache.redis.serializer.StringRedisSerializer
import redis.clients.jedis.Client
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.Transaction

import spock.lang.*

/**
 * {@link org.hibernate.cache.redis.jedis.JedisClient} 테스트
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 13. 5. 1. 오후 3:05
 */
class JedisClientTest extends Specification {

    private JedisClient client = new JedisClient()


    public void setup() {
        client.flushDb()
    }

    void 'connection'() {
        client.ping() == 'pong'
    }

    void 'jedisPool'() {
        MultiThreadTestTool.runTasks(100, new Runnable() {
            @Override
            public void run() {
                client.ping()
                try {
                    Thread.sleep(1)
                } catch (InterruptedException ignored) {}
            }
        })
    }

    void "get and set"() {
        when:
        client.set(JedisClient.DEFAULT_REGION_NAME, "key", 123, -1)
        
        then:
        client.get(JedisClient.DEFAULT_REGION_NAME, "key") == 123
    }

    void "expire"() {
        when:
        client.set(JedisClient.DEFAULT_REGION_NAME, "expireTest", "Value", 1)
        
        then:
        client.get(JedisClient.DEFAULT_REGION_NAME, "expireTest") == "Value"
        
        Thread.sleep(1500)
        
        when:
        client.expire(JedisClient.DEFAULT_REGION_NAME)

        then:
        client.get(JedisClient.DEFAULT_REGION_NAME, "expireTest") == null
    }

    void "flushDb"() {
        when:
        client.set(JedisClient.DEFAULT_REGION_NAME, "a", "a")

        then:
        client.dbSize() > 0

        when:
        client.flushDb()

        then:
        client.dbSize() == 0
    }

    void "delete"() {
        when:
        client.set(JedisClient.DEFAULT_REGION_NAME, "d", "d")

        then:
        client.get(JedisClient.DEFAULT_REGION_NAME, "d") == "d"
        client.exists(JedisClient.DEFAULT_REGION_NAME, "d")

        when:
        client.del(JedisClient.DEFAULT_REGION_NAME, "d")

        then:
        client.get(JedisClient.DEFAULT_REGION_NAME, "d") == null
        !client.exists(JedisClient.DEFAULT_REGION_NAME, "d")
    }

    void 'mget'() {
        given:
        def keys = (0..99).toList()
        keys.each { i ->
            client.set(JedisClient.DEFAULT_REGION_NAME, i, i, -1)
        }

        when:
        List<Object> values = client.mget(JedisClient.DEFAULT_REGION_NAME, keys)

        then:
        values.size() == keys.size()
    }

    void 'mdel'() {
        given:
        def keys = (0..99).toList()
        keys.each { i ->
            client.set(JedisClient.DEFAULT_REGION_NAME, i, i, -1)
        }

        when:
        List<Object> values = client.mget(JedisClient.DEFAULT_REGION_NAME, keys)

        then:
        values.size() == keys.size()

        when:
        client.mdel(JedisClient.DEFAULT_REGION_NAME, keys)

        then:
        !keys.any { i ->
            client.get(JedisClient.DEFAULT_REGION_NAME, i)
        }
    }

    void 'keys in region'() {
        given:
        client.flushDb()

        def keys = (0..99).toList()
        keys.each { i ->
            client.set(JedisClient.DEFAULT_REGION_NAME, i, i, -1)
        }

        when:
        Set<Object> keysInRegion = client.keysInRegion(JedisClient.DEFAULT_REGION_NAME)
        then:
        keysInRegion.size() == keys.size()

        when:
        client.deleteRegion(JedisClient.DEFAULT_REGION_NAME)
        keysInRegion = client.keysInRegion(JedisClient.DEFAULT_REGION_NAME)

        then:
        keysInRegion.size() == 0
    }

    void 'nextTimestamp with no existing value'() {
        given:
        long currentMillis = System.currentTimeMillis()

        when:
        long nextTimestamp = client.nextTimestamp("cacheTest")

        then:
        nextTimestamp > currentMillis
    }

    void 'nextTimestamp with existing value'() {
        when:
        long timestampOne = client.nextTimestamp("cacheTest")
        long timestampTwo = client.nextTimestamp("cacheTest")

        then:
        timestampTwo > timestampOne
    }

    void 'nextTimestamp with existing future value'() {
        given:
        long futureTimestamp = System.currentTimeMillis()+100000
        setTimestamp("cacheTest", futureTimestamp)

        when:
        long nextTimestamp = client.nextTimestamp("cacheTest")

        then:
        nextTimestamp == futureTimestamp+1
    }

    void 'nextTimestamp increments if update fails'() {
        given:
        JedisPool pool = Mock()
        client = new JedisClient(pool)
        Jedis jedis = Mock()
        pool.resource >> jedis

        Client mockClient = Mock()
        Transaction spyTrans = Spy(Transaction, constructorArgs: [mockClient])
        jedis.multi() >> spyTrans

        when:
        long nextTimestamp = client.nextTimestamp("cacheTest")

        then:
        _ * spyTrans.exec() >> null// fake the watch failing
        1 * jedis.incr(_ as byte[]) >> 1000L

        and:
        nextTimestamp == 1000L
    }

    private void setTimestamp(String cacheKey, long timestamp) {
        JedisPool pool = new JedisPool("localhost")
        Jedis jedis = pool.getResource()
        StringRedisSerializer keySerializer = new StringRedisSerializer()
        SnappyRedisSerializer<Object> serializer = new SnappyRedisSerializer<Object>()
        jedis.set(keySerializer.serialize(cacheKey), serializer.serialize(timestamp))
    }
}
