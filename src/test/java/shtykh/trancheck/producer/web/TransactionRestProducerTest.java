package shtykh.trancheck.producer.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import shtykh.trancheck.MainApplication;
import shtykh.trancheck.TransactionChecker;
import shtykh.trancheck.TransactionCheckerTest;
import shtykh.trancheck.dao.TransactionDaoMock;
import shtykh.trancheck.data.TransactionCheck;
import shtykh.trancheck.data.TransactionJson;
import shtykh.trancheck.data.rest.TransactionCheckList;
import shtykh.trancheck.data.rest.TransactionJsonList;
import shtykh.trancheck.print.TransactionCheckRestPrinter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
		TransactionCheckRestPrinter.class,
		TransactionRestProducer.class,
		TransactionDaoMock.class,
		TransactionChecker.class,
		MainApplication.class})
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class TransactionRestProducerTest extends TransactionCheckerTest {
	private static final TransactionJsonList REQUEST = IntStream.range(0, 11)
			.mapToObj(id -> new TransactionJson(id, id * 100.))
			.collect(Collectors.toCollection(TransactionJsonList::new));
	private RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		restTemplate = new TestRestTemplate();
	}

	@Test
	public void testCheck() throws Exception {
		ResponseEntity<TransactionCheckList> entity =
				restTemplate.postForEntity("http://localhost:9000/check", REQUEST, TransactionCheckList.class);
		TransactionCheckList checkList = entity.getBody();
		Assert.assertEquals(REQUEST.size(), checkList.size());
		Assert.assertEquals(10, checkList.stream().filter(t -> t.getOriginalAmount() != null).count());
		Assert.assertEquals(1, checkList.stream().filter(TransactionCheck::isAmountsMatch).count());
		assertsCheckMany(checkList.stream());
	}
}