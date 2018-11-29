package com.recommendationengine.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	@RequestMapping(value="/")
	public String hello() {
	return "Hello World from Root Controller...!";
	}
}
