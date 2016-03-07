package shtykh.trancheck.print;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shtykh.trancheck.data.TransactionCheck;

import java.util.Collection;

/**
 * Created by shtykh on 06/03/16.
 */
@Component
public class TransactionCheckCsvPrinter implements TransactionCheckPrinter<String> {
	@Override
	public String print(Collection<TransactionCheck> checks) {
		StringBuilder sb = new StringBuilder("ID;AMOUNT;ORIGINAL_AMOUNT;AMOUNTS_MATCH\n");
		checks.forEach(t->sb.append(print(t) + "\n"));
		return sb.toString();
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
