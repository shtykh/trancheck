package shtykh.trancheck.data;

/**
 * Created by shtykh on 06/03/16.
 */
public class TransactionDb extends Transaction {
	private final String data;

	public TransactionDb(int id, double amount, String data) {
		super(id, amount);
		this.data = data;
	}

	@Override
	public String toString() {
		return "TransactionCsv{" +
				super.toString() +
				", data=" + data +
				'}';
	}
}
