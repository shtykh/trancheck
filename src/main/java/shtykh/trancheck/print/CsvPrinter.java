package shtykh.trancheck.print;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shtykh.trancheck.data.Transaction;

import java.util.Optional;

/**
 * Created by shtykh on 06/03/16.
 */
@Component
public class CsvPrinter implements TransactionCheckPrinter {
	@Override
	public String check(Transaction tested, Optional<? extends Transaction> original) {
		return StringUtils.arrayToDelimitedString(
				new String[]{
						String.valueOf(tested.getId()),
						String.valueOf(tested.getAmount()),
						String.valueOf(original.isPresent()?original.get().getAmount():"null"),
						String.valueOf(original.isPresent()?Double.compare(original.get().getAmount(), tested.getAmount()) == 0:"false"),
				}, ";");
	}
}
