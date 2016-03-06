package shtykh.trancheck.data;

/**
 * Created by shtykh on 05/03/16.
 */
public class Transaction {
	private final int id;
	private final double amount;

	public Transaction(int id, double amount) {
		this.id = id;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"id=" + id +
				", amount=" + amount +
				'}';
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}
}
