package shtykh.trancheck.print;

import shtykh.trancheck.data.TransactionCheck;

import java.util.stream.Stream;

/**
 * Created by shtykh on 06/03/16.
 */
public interface TransactionCheckPrinter<Out> {
	Out print(Stream<TransactionCheck> checks);
}
