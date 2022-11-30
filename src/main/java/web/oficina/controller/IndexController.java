package web.oficina.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@GetMapping(value = {"/", "/index.html"} )
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		logger.trace("Entrou em login");
		logger.trace("Encaminhando para a view login");
		return "login";
	}
	
}