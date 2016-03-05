package shtykh.trancheck;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by shtykh on 08/04/15.
 */
@EnableAutoConfiguration
public class MainApplication {
	private static final Logger log = Logger.getLogger(MainApplication.class);

	public static void main(String[] args) throws Exception {
		try {
			Object[] classes = new Object[]{
					MainApplication.class,
					HelloPage.class,
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
