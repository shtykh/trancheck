package shtykh.trancheck.producer;

import org.springframework.beans.factory.annotation.Autowired;
import shtykh.trancheck.TransactionChecker;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.ex.TransactionException;
import shtykh.trancheck.print.TransactionCheckPrinter;

import java.util.Collection;

/**
 * Created by shtykh on 06/03/16.
 */
public abstract class TransactionProducer<Source, T extends Transaction, Out> {
	@Autowired
	TransactionChecker checker;
	protected abstract Collection<T> toTransactions(Source s) throws TransactionException;
	protected abstract TransactionCheckPrinter<Out> getPrinter();
	public Out check(Source s) throws TransactionException {
		return getPrinter().print(checker.check(toTransactions(s)));
	}
}
