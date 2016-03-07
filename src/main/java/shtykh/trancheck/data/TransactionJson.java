package shtykh.trancheck.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by shtykh on 07/03/16.
 */
public class TransactionJson extends Transaction {
	public TransactionJson(@JsonProperty("id") int id, @JsonProperty("amount") double amount) {
		super(id, amount);
	}
}
