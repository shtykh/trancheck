package shtykh.trancheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shtykh.trancheck.dao.TransactionDao;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.data.TransactionCheck;
import shtykh.trancheck.data.TransactionDb;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shtykh on 05/03/16.
 */
@Controller
public class TransactionChecker {
	@Autowired
	private TransactionDao dao;

	private Comparator<Transaction> byId =
			Comparator.comparingInt(Transaction::getId);

	public Stream<TransactionCheck> check(Collection<? extends Transaction> transactions) {
		LinkedList<TransactionDb> originals = dao.getByIds(transactions
												.stream()
												.map(Transaction::getId)
												.collect(Collectors.toSet()))
									.stream()
									.sorted(byId)
									.collect(Collectors.toCollection(LinkedList::new));
		return transactions.stream().sorted(byId).map(t -> matchWithFirst(t, originals));
	}

	public TransactionCheck check(Transaction t) {
		return new TransactionCheck(t, dao.getById(t.getId()));
	}

	public TransactionCheck matchWithFirst(Transaction t, LinkedList<TransactionDb> originals) {
		while (! originals.isEmpty()) {
			TransactionDb first = originals.getFirst();
			if(t.getId()==first.getId()) {
				return new TransactionCheck(t, first);
			} else if (t.getId() > first.getId()) {
				originals.removeFirst();
			}
		}
		return new TransactionCheck(t);
	}
}
