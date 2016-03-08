package shtykh.trancheck.producer.file;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shtykh.trancheck.data.TransactionCsv;
import shtykh.trancheck.print.TransactionCheckCsvPrinter;
import shtykh.trancheck.print.TransactionCheckPrinter;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shtykh on 06/03/16.
 */
@Component
public class TransactionCsvProducer extends TransactionFileProducer<TransactionCsv> {
	private static Logger log = Logger.getLogger(TransactionCsvProducer.class);

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
	protected Collection<TransactionCsv> readFromFile(File file) throws IOException {
		return CSVParser.parse(file, Charset.forName(charset), csvFormat)
				.getRecords()
				.stream()
				.flatMap(this::toTransaction)
				.collect(Collectors.toList());
	}

	private Stream<TransactionCsv> toTransaction(CSVRecord record) {
		try{
			int pid = Integer.decode(record.get("PID"));
			double pamount = Double.valueOf(record.get("PAMOUNT"));
			Date pdata = dateFormat.parse(record.get("PDATA"));
			return Stream.of(new TransactionCsv(pid, pamount, pdata));
		} catch (Exception e) {
			log.warn(record.toString() + " could not be parsed into transaction", e);
			return Stream.empty();
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
