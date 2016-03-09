package shtykh.trancheck;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shtykh.trancheck.dao.TransactionDaoMock;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.data.TransactionCheck;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
		TransactionDaoMock.class,
		TransactionChecker.class})
public abstract class TransactionCheckerTest extends TestCase {
	private static final double DELTA = 0.0001;
	@Autowired
	TransactionChecker checker;
	@Autowired
	TransactionDaoMock daoMock;

	private static final Collection<Transaction> REQUEST = IntStream.range(-1, 11)
			.mapToObj(id -> new Transaction(id, id * 100.))
			.collect(Collectors.toList());

	@Test
	public void testCheckMany() throws Exception {
		assertsCheckMany(checker.check(REQUEST));
	}

	protected void assertsCheckMany(Stream<TransactionCheck> checks) {
		checks.forEach(ck-> {
				if (! daoMock.exists(ck)) {
					testCheckDoesntExists(ck);
				} else if (daoMock.matches(ck)) {
					testCheckMatches(ck);
				} else {
					testCheckDoesntMatch(ck);
				}}
		);
	}

	@Test
	public void testOne() throws Exception {
		testTransactionsMatches(new Transaction(1, 10));
		testTransactionDoesntMatch(new Transaction(2, 20.001));
		testTransactionDoesntExist(new Transaction(-10, 10));
	}
	
	public void testTransactionsMatches(Transaction original) throws Exception {
		TransactionCheck check = checker.check(original);
		Assert.assertEquals(original.getId(), check.getId());
		Assert.assertEquals(original.getAmount(), check.getAmount(), DELTA);
		testCheckMatches(check);
	}

	private void testCheckMatches(TransactionCheck check) {
		Assert.assertEquals(check.getOriginalAmount(), Double.valueOf(check.getAmount()));
		Assert.assertTrue(check.isAmountsMatch());
		Assert.assertNotNull(check.getOriginalAmount());
		Assert.assertEquals(check.getOriginalAmount(), Double.valueOf(check.getAmount()));
	}

	public void testTransactionDoesntMatch(Transaction original) throws Exception {
		TransactionCheck check = checker.check(original);
		Assert.assertEquals(original.getId(), check.getId());
		Assert.assertEquals(original.getAmount(), check.getAmount(), DELTA);
		testCheckDoesntMatch(check);
	}

	private void testCheckDoesntMatch(TransactionCheck check) {
		Assert.assertFalse(check.isAmountsMatch());
		Assert.assertNotNull(check.getOriginalAmount());
	}

	public void testTransactionDoesntExist(Transaction original) throws Exception {
		TransactionCheck check = checker.check(original);
		Assert.assertEquals(original.getId(), check.getId());
		Assert.assertEquals(original.getAmount(), check.getAmount(), DELTA);
		testCheckDoesntExists(check);
	}

	private void testCheckDoesntExists(TransactionCheck check) {
		Assert.assertFalse(check.isAmountsMatch());
		Assert.assertNull(check.getOriginalAmount());
	}
}