package shtykh.trancheck.provider;

import shtykh.trancheck.data.Transaction;

import java.io.File;
import java.util.Collection;

/**
 * Created by shtykh on 06/03/16.
 */
public abstract class TransactionFileReader<T extends Transaction> implements TransactionProvider<T> {
	@Override
	public final Collection<T> getTransactions() {
		File file = initFile();
		return readFromFile(file);
	}

	private File initFile() {
		return new File(getPath());
	}

	protected abstract String getPath();

	protected abstract Collection<T> readFromFile(File file);
}
