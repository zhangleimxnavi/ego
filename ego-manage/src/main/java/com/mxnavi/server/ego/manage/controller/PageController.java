package com.mxnavi.server.ego.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PageController {
	@RequestMapping("/")
	public String showIndexPage(){
		
		return "index";
	}
	

	@RequestMapping("{page}")
	public String showPage(@PathVariable("page") String page){
		
		return page;
	}
	
	
	
	
	
	
	
}
