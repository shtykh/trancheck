package shtykh.trancheck.producer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shtykh.trancheck.data.TransactionCheck;
import shtykh.trancheck.data.TransactionJson;
import shtykh.trancheck.print.TransactionCheckPrinter;
import shtykh.trancheck.print.TransactionCheckTrivialPrinter;
import shtykh.trancheck.producer.TransactionProducer;

import java.util.Collection;

/**
 * Created by shtykh on 07/03/16.
 */
@Controller
public class TransactionRestProducer extends TransactionProducer<Collection<TransactionJson>, TransactionJson, Collection<TransactionCheck>> {
	@Autowired
	private TransactionCheckTrivialPrinter printer;

	@Override
	protected Collection<TransactionJson> toTransactions(Collection<TransactionJson> jsonArray) {
		return jsonArray;
	}

	@Override
	protected TransactionCheckPrinter<Collection<TransactionCheck>> getPrinter() {
		return printer;
	}

	@RequestMapping(value="/check", method= RequestMethod.POST, produces="application/json", consumes = "application/json")
	@ResponseBody
	public Collection<TransactionCheck> check(@RequestBody Collection<TransactionJson> json) {
		return super.check(json);
	}
}
