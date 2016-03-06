package shtykh.trancheck.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by shtykh on 05/03/16.
 */
public interface Dao<K, V> {
	default List<V> getByIds(K... keys) {
		return getByIds(Arrays.asList(keys));
	}

	List<V> getByIds(Collection<K> keys);

	Optional<V> getById (K key);

}
