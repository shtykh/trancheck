package shtykh.trancheck.provider;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shtykh.trancheck.data.TransactionCsv;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by shtykh on 06/03/16.
 */
@Component
public class TransactionCsvProvider extends TransactionFileReader<TransactionCsv> {
	@Value("${file.csv.path}")
	private String path;
	@Value("${file.csv.charset}")
	private String charset;
	@Value("${file.csv.dateFormatString}")
	private String dateFormatString;
	private DateFormat dateFormat;
	private CSVFormat csvFormat = CSVFormat
			.newFormat(';')
			.withHeader("PID", "PAMOUNT", "PDATA")
			.withSkipHeaderRecord(true)
			.withRecordSeparator(";\n");

	@PostConstruct
	private void initDateFormat() {
		dateFormat = new SimpleDateFormat(dateFormatString);
	}

	@Override
	protected String getPath() {
		return path;
	}

	@Override
	protected Collection<TransactionCsv> readFromFile(File file) {
		try {
			CSVParser parser = CSVParser.parse(file, Charset.forName(charset), csvFormat);
			Collection<TransactionCsv> transactions = new ArrayList<>();
			parser.forEach(record -> {
				try{
					int pid = Integer.decode(record.get("PID"));
					double pamount = Double.valueOf(record.get("PAMOUNT"));
					Date pdata = null;
					try {
						pdata = dateFormat.parse(record.get("PDATA"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					TransactionCsv transaction = new TransactionCsv(pid, pamount, pdata);
					transactions.add(transaction);
				} catch (Exception ignored) {}
			});
			return transactions;
		} catch (Exception e) {
			return null;
		}
	}
}
