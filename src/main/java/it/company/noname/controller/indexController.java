package it.company.noname.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class indexController {
	
	
	@RequestMapping(value = "main")
	public String main() {
		
		return "main";
	}
}
