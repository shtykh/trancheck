package shtykh.trancheck.print;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import shtykh.trancheck.data.TransactionCheck;

import java.util.Collection;

/**
 * Created by shtykh on 07/03/16.
 */
@Component
public class TransactionCheckTrivialPrinter implements TransactionCheckPrinter<Collection<TransactionCheck>> {
	private static final Logger log = Logger.getLogger(TransactionCheckTrivialPrinter.class);
	@Override
	public Collection<TransactionCheck> print(Collection<TransactionCheck> checks) {
		return checks;
	}
}
