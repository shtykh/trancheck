package shtykh.trancheck.producer.file;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shtykh.trancheck.data.TransactionCsv;
import shtykh.trancheck.print.TransactionCheckPrinter;
import shtykh.trancheck.print.TransactionCheckCsvPrinter;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by shtykh on 06/03/16.
 */
@Component
public class TransactionCsvProducer extends TransactionFileProducer<TransactionCsv> {
	@Autowired
	private TransactionCheckCsvPrinter printer;
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
	protected Collection<TransactionCsv> readFromFile(File file) {
		try {
			CSVParser parser = CSVParser.parse(file, Charset.forName(charset), csvFormat);
			Collection<TransactionCsv> transactions = new ArrayList<>();
			parser.forEach(record -> {
				try{
					int pid = Integer.decode(record.get("PID"));
					double pamount = Double.valueOf(record.get("PAMOUNT"));
					Date pdata = null;
					pdata = dateFormat.parse(record.get("PDATA"));
					TransactionCsv transaction = new TransactionCsv(pid, pamount, pdata);
					transactions.add(transaction);
				} catch (Exception ignored) {}// TODO
			});
			return transactions;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected String getOutputPath() {
		return getPath().replace(".csv", "_out.csv");
	}

	@Override
	protected String getPath() {
		return path;
	}

	@Override
	protected TransactionCheckPrinter getPrinter() {
		return printer;
	}
}
