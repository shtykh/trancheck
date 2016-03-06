package shtykh.trancheck.print;

import shtykh.trancheck.data.Transaction;

import java.util.Optional;

/**
 * Created by shtykh on 06/03/16.
 */
public interface TransactionCheckPrinter {
	public String check(Transaction some, Optional<? extends Transaction> original);
}
