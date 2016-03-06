package shtykh.trancheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shtykh.trancheck.dao.TransactionDao;
import shtykh.trancheck.data.Transaction;
import shtykh.trancheck.print.TransactionCheckPrinter;
import shtykh.trancheck.provider.TransactionProvider;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Created by shtykh on 05/03/16.
 */
@Controller
public class TransactionCheckService {
	@Autowired
	private TransactionDao dao;
	@Autowired
	private TransactionProvider provider;
	@Autowired
	private TransactionCheckPrinter printer;

	@RequestMapping("/provided")
	@ResponseBody
	public String provided() {
		StringBuilder sb = new StringBuilder("Provided: <br>");
		provider.getTransactions().stream().forEach(t->sb.append(t.toString() + "<br>"));
		return sb.toString();
	}

	@RequestMapping("/check")
	@ResponseBody
	public String check() {
		StringBuilder sb = new StringBuilder("ID;TESTED;DB;MATCHES\n");
		Collection<Transaction> transactions = provider.getTransactions();
		transactions.forEach(t->sb.append(printer.check(t, dao.getById(t.getId()))+"\n"));
		String csv = sb.toString();
		return csv.replace("\n", "<br>");
	}

	@RequestMapping("/get/{id}")
	@ResponseBody
	public String getById(@PathVariable("id") int id) {
		try{
			return dao.getById(id).get().toString();
		} catch (NoSuchElementException nse) {
			return id + ": " + nse.getMessage();
		}
	}
}
