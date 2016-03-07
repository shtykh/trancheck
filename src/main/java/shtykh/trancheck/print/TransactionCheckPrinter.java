package shtykh.trancheck.print;

import shtykh.trancheck.data.TransactionCheck;

import java.util.Collection;

/**
 * Created by shtykh on 06/03/16.
 */
public interface TransactionCheckPrinter<Out> {
	Out print(Collection<TransactionCheck> checks);
}
