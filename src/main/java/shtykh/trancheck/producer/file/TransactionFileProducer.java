package shtykh.trancheck.producer.file;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.ex.TransactionException;
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
	public final Collection<T> toTransactions(File file) throws TransactionException {
		try {
			return readFromFile(file);
		} catch (IOException e) {
			throw new TransactionException("Read transactions from file error", e);
		}
	}

	private File initFile() {
		return new File(getPath());
	}

	protected abstract String getPath();

	protected abstract Collection<T> readFromFile(File file) throws IOException;
	
	public final void check() throws IOException, TransactionException {
		writeToFile(check(initFile()));
	}

	protected void writeToFile(String checkingResult) throws IOException {
		File f = new File(getOutputPath());
		FileUtils.writeStringToFile(f, checkingResult, "UTF-8");
	}

	protected abstract String getOutputPath();
}
