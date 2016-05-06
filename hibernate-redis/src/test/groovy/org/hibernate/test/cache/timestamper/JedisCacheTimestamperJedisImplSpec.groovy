package org.hibernate.test.cache.timestamper

import org.hibernate.cache.redis.jedis.JedisClient
import org.hibernate.cache.redis.timestamper.JedisCacheTimestamperJedisImpl
import org.hibernate.cfg.Settings
import org.hibernate.cfg.TestingSettingsBuilder

import spock.lang.*

class JedisCacheTimestamperJedisImplSpec extends Specification {
    public static final String CACHE_REGION_PREFIX_FIELD_NAME = "cacheRegionPrefix"

    JedisClient jedisClient = Mock()

    private TestingSettingsBuilder settingsBuilder

    private Settings settings

    private Properties properties

    private JedisCacheTimestamperJedisImpl timestamper

    def setup() {
        settingsBuilder = new TestingSettingsBuilder()
        properties = new Properties()
    }

    void constructor() {
        given:
        givenTimestamperWithCacheRegionPrefix("cachetest")

        when:
        String timestampCacheKey = timestamper.getTimestampCacheKey()

        then:
        timestampCacheKey == "cachetest." + JedisCacheTimestamperJedisImpl.TIMESTAMP_KEY
    }

    void "constructor without cacheRegionPrefix"() {
        given:
        givenTimestamperWithCacheRegionPrefix(null)

        when:
        String timestampCacheKey = timestamper.getTimestampCacheKey()

        then:
        timestampCacheKey == JedisCacheTimestamperJedisImpl.TIMESTAMP_KEY
    }

    void next() {
        given:
        givenTimestamperWithCacheRegionPrefix("myservice")
        String cacheKey = timestamper.getTimestampCacheKey()

        long expected = 12345L

        when:
        long next = timestamper.next()

        then:
        jedisClient.nextTimestamp(cacheKey) >> expected

        next == expected
    }

    private void givenTimestamperWithCacheRegionPrefix(String cacheRegionPrefix) {
        settings = settingsBuilder.setField(CACHE_REGION_PREFIX_FIELD_NAME, cacheRegionPrefix).build()

        timestamper = new JedisCacheTimestamperJedisImpl()
        timestamper.setSettings(settings)
        timestamper.setProperties(properties)
        timestamper.setJedisClient(jedisClient)
    }
}