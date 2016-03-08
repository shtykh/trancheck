package shtykh.trancheck.print;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import shtykh.trancheck.data.TransactionCheck;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shtykh on 07/03/16.
 */
@Component
public class TransactionCheckRestPrinter implements TransactionCheckPrinter<Collection<TransactionCheck>> {
	private static final Logger log = Logger.getLogger(TransactionCheckRestPrinter.class);
	@Override
	public Collection<TransactionCheck> print(Stream<TransactionCheck> checks) {
		return checks.collect(Collectors.toList());
	}
}
