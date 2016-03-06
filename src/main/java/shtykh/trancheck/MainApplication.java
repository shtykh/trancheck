package shtykh.trancheck;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import shtykh.trancheck.dao.TransactionDaoImpl;
import shtykh.trancheck.print.CsvPrinter;
import shtykh.trancheck.provider.TransactionCsvProvider;

/**
 * Created by shtykh on 08/04/15.
 */
@EnableAutoConfiguration
@ImportResource("classpath:spring/context.xml")
public class MainApplication {
	private static final Logger log = Logger.getLogger(MainApplication.class);

	public static void main(String[] args) throws Exception {
		try {
			Object[] classes = new Object[]{
					CsvPrinter.class,
					TransactionCsvProvider.class,
					TransactionDaoImpl.class,
					MainApplication.class,
					TransactionCheckService.class,
			};
			SpringApplication app = new SpringApplicationBuilder()
					.sources(classes)
					.build();
			app.run(args);
			log.info("Application launched successfully");
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
}
