package shtykh.trancheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shtykh.trancheck.dao.TransactionDao;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.data.TransactionCheck;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by shtykh on 05/03/16.
 */
@Controller
public class TransactionChecker {
	@Autowired
	private TransactionDao dao;

	public Collection<TransactionCheck> check(Collection<? extends Transaction> transactions) {
		return transactions.stream().map(t -> check(t)).collect(Collectors.toList());
	}

	public TransactionCheck check(Transaction t) {
		return new TransactionCheck(t, dao.getById(t.getId()));
	}
}
