package shtykh.trancheck.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shtykh.trancheck.data.TransactionDb;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by shtykh on 05/03/16.
 */
@Component
public class TransactionDaoImpl implements TransactionDao {
	private static final String GET_BY_ID_QUERY = 
			"SELECT t.id, t.amount, t.data FROM transactions t " +
			"WHERE t.id IN ";

	private static final RowMapper<TransactionDb> TRANSACTION_MAPPER =
			(resultSet, i) -> new TransactionDb(resultSet.getInt("id"), resultSet.getDouble("amount"), resultSet.getString("data"));

	private static String getQueryForSize(int size) {
		String[] array = new String[size];
		Arrays.fill(array, "?");
		return GET_BY_ID_QUERY + "(" + StringUtils.arrayToDelimitedString(array, ",") + ")";
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<TransactionDb> getByIds(Collection<Integer> keys) {
		throw new NotImplementedException(); // TODO
	}

	@Override
	public Optional<TransactionDb> getById(Integer key) {
		return jdbcTemplate.query(getQueryForSize(1),
				preparedStatement -> {preparedStatement.setInt(1, key);},
				TRANSACTION_MAPPER)
				.stream()
				.findAny();
	}
}
