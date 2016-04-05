package org.hibernate.cache.redis.serializer;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Serializer using Fast-Serialization
 *
 * @author Sunghyouk Bae
 */
public class FstRedisSerializer<T> implements RedisSerializer<T> {

    private static final FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
    private static final Logger log = LoggerFactory.getLogger(FstRedisSerializer.class);

    /**
     * Provides access to serialization configuration, to inject custom ClassLoaders
     * among other things.
     * @return serialization configuration.
     */
    public static FSTConfiguration getConf() {
	return conf;
    }

    @Override
    public byte[] serialize(final T graph) {
        if (graph == null)
            return EMPTY_BYTES;

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()){
            FSTObjectOutput oos = conf.getObjectOutput(os);
            oos.writeObject(graph);
            oos.flush();

            return os.toByteArray();
        } catch (Exception e) {
            log.warn("Fail to serializer graph. graph=" + graph, e);
            return EMPTY_BYTES;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(final byte[] bytes) {
        if (SerializationTool.isEmpty(bytes))
            return null;

        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes)){
            FSTObjectInput ois = conf.getObjectInput(is);
            return (T) ois.readObject();
        } catch (Exception e) {
            log.warn("Fail to deserialize bytes.", e);
            return null;
        }
    }
}
