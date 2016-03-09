package shtykh.trancheck.producer.file;

import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.ex.TransactionException;
import shtykh.trancheck.producer.TransactionProducer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static org.apache.commons.io.FileUtils.writeStringToFile;

/**
 * Created by shtykh on 06/03/16.
 */
public abstract class TransactionFileProducer<T extends Transaction> extends TransactionProducer<File, T, String> {
	@Override
	public final Collection<T> toTransactions(File file) throws TransactionException {
		try {
			return readFromFile(file);
		} catch (IOException e) {
			throw new TransactionException("Read transactions from file error", e);
		}
	}

	public final void check() throws TransactionException {
		try {
			writeToFile(check(initFile()));
		} catch (IOException e) {
			throw new TransactionException("Write transaction check result to file error", e);
		}
	}

	private File initFile() {
		return new File(getPath());
	}

	protected void writeToFile(String checkingResult) throws IOException {
		writeStringToFile(new File(getOutputPath()), checkingResult, getCharset());
	}

	protected abstract Collection<T> readFromFile(File file) throws IOException;

	protected abstract String getPath();

	protected abstract String getOutputPath();

	protected abstract String getCharset();
}
