package shtykh.trancheck.data;

import java.util.Date;

/**
 * Created by shtykh on 06/03/16.
 */
public class TransactionCsv extends Transaction {
	private final Date date;
	public TransactionCsv(int id, double amount, Date date) {
		super(id, amount);
		this.date = date;
	}

	@Override
	public String toString() {
		return "TransactionCsv{" + 
				super.toString() +
				", date=" + date +
				'}';
	}
}
