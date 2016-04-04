/*
 * Copyright 2011-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

package org.hibernate.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.redis.util.JedisTool;
import org.hibernate.cfg.Settings;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A singleton RedisRegionFactory implementation.
 *
 * @author sunghyouk.bae@gmail.com
 * @since 13. 4. 6. 오전 12:31
 */
@Slf4j
public class SingletonRedisRegionFactory extends AbstractRedisRegionFactory {

    private static final AtomicInteger referenceCount = new AtomicInteger();

    @Override
    public synchronized void start(Settings settings, Properties properties) throws CacheException {
        log.info("Starting SingletonRedisRegionFactory...");

        this.settings = settings;
        try {
            initializeRegionFactory(settings, JedisTool.loadCacheProperties(properties));
            referenceCount.incrementAndGet();
            log.info("Started SingletonRedisRegionFactory");
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public synchronized void stop() {
        log.debug("Stopping SingletonRedisRegionFactory...");

        if (referenceCount.decrementAndGet() == 0) {
            try {
                destroy();
                log.info("Stopped SingletonRedisRegionFactory");
            } catch (Exception ignored) {
            }
        }
    }

    private static final long serialVersionUID = -7477946174209489184L;
}
