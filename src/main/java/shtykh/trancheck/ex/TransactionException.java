package shtykh.trancheck.ex;

/**
 * Created by shtykh on 08/03/16.
 */
public class TransactionException extends Exception {
	public TransactionException() {
		super();
	}

	public TransactionException(String message) {
		super(message);
	}

	public TransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionException(Throwable cause) {
		super(cause);
	}

	protected TransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
