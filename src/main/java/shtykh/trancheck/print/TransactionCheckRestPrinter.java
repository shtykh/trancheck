package shtykh.trancheck.print;

import org.springframework.stereotype.Component;
import shtykh.trancheck.data.TransactionCheck;
import shtykh.trancheck.data.rest.TransactionCheckList;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shtykh on 07/03/16.
 */
@Component
public class TransactionCheckRestPrinter implements TransactionCheckPrinter<TransactionCheckList> {
	@Override
	public TransactionCheckList print(Stream<TransactionCheck> checks) {
		return checks.collect(Collectors.toCollection(TransactionCheckList::new));
	}
}
