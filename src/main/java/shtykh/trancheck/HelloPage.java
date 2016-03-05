package shtykh.trancheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shtykh on 05/03/16.
 */
@Controller
public class HelloPage {
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello!";
	}
}
