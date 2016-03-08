package shtykh.trancheck.dao;

import org.springframework.stereotype.Component;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.data.TransactionCheck;
import shtykh.trancheck.data.TransactionDb;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by shtykh on 08/03/16.
 */
@Component
public class TransactionDaoMock implements TransactionDao {
	private Map<Integer, TransactionDb> map;
	private int FLOOR = 0;
	private int CEILING = 10;
	private double MULTIPLICATOR = 10.;

	public TransactionDaoMock() {
		super();
		map = new HashMap<>();
		IntStream
				.range(FLOOR, CEILING)
				.forEach(i -> map.put(i, new TransactionDb(i, i * MULTIPLICATOR, String.valueOf(i))));
	}

	public Stream<TransactionDb> getByIds(Collection<Integer> keys) {
		return keys.stream()
				.flatMap(this::getAsStream);
	}

	public Stream<TransactionDb> getAsStream(Integer key) {
		return map.containsKey(key)?Stream.of(map.get(key)):Stream.empty();
	}

	@Override
	public Optional<TransactionDb> getById(Integer key) {
		return Optional.ofNullable(map.get(key));
	}
	
	public boolean exists(Transaction t) {
		return t.getId() < CEILING && t.getId() >= FLOOR;
	}

	public boolean matches(TransactionCheck t) {
		return exists(t)
				&& (t.getAmount() == t.getId() * MULTIPLICATOR);
	}
}
