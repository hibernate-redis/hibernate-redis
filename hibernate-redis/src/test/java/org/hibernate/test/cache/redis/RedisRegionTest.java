package org.hibernate.test.cache.redis;

import org.hibernate.cache.redis.SingletonRedisRegionFactory;
import org.hibernate.cache.redis.strategy.AbstractReadWriteRedisAccessStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.test.strategy.ItemValueExtractor;

import java.util.Map;

/**
 * org.hibernate.test.cache.redis.RedisRegionTest
 *
 * @author sunghyouk.bae@gmail.com
 * @since 13. 4. 6. 오전 12:52
 */
public class RedisRegionTest extends RedisTest {

    @Override
    protected void configCache(Configuration cfg) {
        cfg.setProperty(Environment.CACHE_REGION_FACTORY, SingletonRedisRegionFactory.class.getName());
        cfg.setProperty(Environment.CACHE_PROVIDER_CONFIG, "hibernate-redis.properties");
    }

    @Override
    protected Map getMapFromCacheEntry(final Object entry) {
        final Map map;
        if (entry.getClass()
                 .getName()
                 .equals(AbstractReadWriteRedisAccessStrategy.class.getName() + "$Item")) {
            map = ItemValueExtractor.getValue(entry);
        } else {
            map = (Map) entry;
        }
        return map;
    }
}
