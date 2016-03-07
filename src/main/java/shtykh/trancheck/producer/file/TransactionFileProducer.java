package shtykh.trancheck.producer.file;

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.producer.TransactionProducer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by shtykh on 06/03/16.
 */
public abstract class TransactionFileProducer<T extends Transaction> extends TransactionProducer<File, T, String> {
	private static Logger log = Logger.getLogger(TransactionFileProducer.class);
	@Override
	public final Collection<T> toTransactions(File file) {
		return readFromFile(file);
	}

	private File initFile() {
		return new File(getPath());
	}

	protected abstract String getPath();

	protected abstract Collection<T> readFromFile(File file);
	
	public final void check() {
		try {
			writeToFile(check(initFile()));
		} catch (IOException e) {
			log.error(e);
		}
	}

	protected void writeToFile(String checkingResult) throws IOException {
		File f = new File(getOutputPath());
		FileUtils.writeStringToFile(f, checkingResult, "UTF-8");
	}

	protected abstract String getOutputPath();
}
