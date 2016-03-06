package shtykh.trancheck.provider;

import shtykh.trancheck.data.Transaction;

import java.util.Collection;

/**
 * Created by shtykh on 06/03/16.
 */
public interface TransactionProvider<T extends Transaction> {
	public Collection<T> getTransactions();
}
