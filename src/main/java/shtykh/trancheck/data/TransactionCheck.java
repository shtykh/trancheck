package shtykh.trancheck.data;

import java.util.Optional;

/**
 * Created by shtykh on 07/03/16.
 */
public class TransactionCheck extends Transaction {
	private Double originalAmount;

	public TransactionCheck(Transaction tested, Optional<? extends Transaction> original) {
		super(tested.getId(), tested.getAmount());
		original.ifPresent(o->this.originalAmount=o.getAmount());
	}

	public TransactionCheck(Transaction tested, Transaction original) {
		super(tested.getId(), tested.getAmount());
		this.originalAmount=original.getAmount();
	}

	public TransactionCheck(Transaction tested) {
		super(tested.getId(), tested.getAmount());
	}

	public Double getOriginalAmount() {
		return originalAmount;
	}
	
	public boolean isAmountsMatch() {
		return originalAmount != null && originalAmount.equals(getAmount());
	}

	@Override
	public String toString() {
		return "TransactionCheck{" +
				super.toString() +
				", originalAmount=" + originalAmount +
				'}';
	}
}
