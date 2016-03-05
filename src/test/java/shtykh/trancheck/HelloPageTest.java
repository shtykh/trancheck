package shtykh.trancheck;

import junit.framework.TestCase;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
		MainApplication.class,
		HelloPage.class})
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class HelloPageTest extends TestCase {
	private RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		restTemplate = new TestRestTemplate();
	}
	
	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity =
				restTemplate.getForEntity("http://localhost:9000/", String.class);
		Assert.assertEquals("Hello!", entity.getBody());
	}
}