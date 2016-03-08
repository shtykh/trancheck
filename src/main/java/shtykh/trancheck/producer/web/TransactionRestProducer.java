package shtykh.trancheck.producer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shtykh.trancheck.data.rest.TransactionCheckList;
import shtykh.trancheck.data.TransactionJson;
import shtykh.trancheck.data.rest.TransactionJsonList;
import shtykh.trancheck.ex.TransactionException;
import shtykh.trancheck.print.TransactionCheckPrinter;
import shtykh.trancheck.print.TransactionCheckRestPrinter;
import shtykh.trancheck.producer.TransactionProducer;

import java.util.Collection;

/**
 * Created by shtykh on 07/03/16.
 */
@Controller
public class TransactionRestProducer extends TransactionProducer<TransactionJsonList, TransactionJson, TransactionCheckList> {
	@Autowired
	private TransactionCheckRestPrinter printer;

	@Override
	protected Collection<TransactionJson> toTransactions(TransactionJsonList jsonArray) {
		return jsonArray;
	}

	@Override
	protected TransactionCheckPrinter<TransactionCheckList> getPrinter() {
		return printer;
	}

	@RequestMapping(value="/check", method= RequestMethod.POST)
	@ResponseBody
	public TransactionCheckList check(@RequestBody TransactionJsonList json) throws TransactionException {
		return super.check(json);
	}
}
