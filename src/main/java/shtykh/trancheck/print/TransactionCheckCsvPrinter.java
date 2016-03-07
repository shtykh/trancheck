package shtykh.trancheck.print;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shtykh.trancheck.data.TransactionCheck;

import java.util.stream.Stream;

/**
 * Created by shtykh on 06/03/16.
 */
@Component
public class TransactionCheckCsvPrinter implements TransactionCheckPrinter<String> {
	@Override
	public String print(Stream<TransactionCheck> checks) {
		return checks
				.map(this::print)
				.reduce("ID;AMOUNT;ORIGINAL_AMOUNT;AMOUNTS_MATCH", 
						(accumulator, string) -> accumulator + "\n" + string);
	}

	public String print(TransactionCheck check) {
		return StringUtils.arrayToDelimitedString(
				new String[]{
						String.valueOf(check.getId()),
						String.valueOf(check.getAmount()),
						String.valueOf(check.getOriginalAmount()),
						String.valueOf(check.isAmountsMatch())
				}, ";");
	}
}
