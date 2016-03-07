package shtykh.trancheck;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import shtykh.trancheck.dao.TransactionDaoImpl;
import shtykh.trancheck.print.TransactionCheckCsvPrinter;
import shtykh.trancheck.print.TransactionCheckTrivialPrinter;
import shtykh.trancheck.producer.file.TransactionCsvProducer;
import shtykh.trancheck.producer.web.TransactionRestProducer;

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
					TransactionCheckCsvPrinter.class,
					TransactionCsvProducer.class,
					TransactionCheckTrivialPrinter.class,
					TransactionRestProducer.class,
					TransactionDaoImpl.class,
					TransactionChecker.class,
					MainApplication.class,
			};
			SpringApplication app = new SpringApplicationBuilder()
					.sources(classes)
					.build();
			ConfigurableApplicationContext ctx = app.run(args);
			ctx.getBean(TransactionCsvProducer.class).check();
			log.info("Application launched successfully");
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
}
