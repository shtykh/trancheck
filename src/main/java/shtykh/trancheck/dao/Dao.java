package shtykh.trancheck.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by shtykh on 05/03/16.
 */
public interface Dao<K, V> {
	default Stream<V> getByIds(K... keys) {
		return getByIds(Arrays.asList(keys));
	}

	Stream<V> getByIds(Collection<K> keys);

	Optional<V> getById (K key);

}
