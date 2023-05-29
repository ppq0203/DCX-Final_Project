package project.momento.monopoly.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class MonopolyController {
	@Autowired
	private SignService SignService;
	

	/*
	 * 
	 */
	@RequestMapping(value="/marble.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String marbleMain( Model model) {
	    return "content/marble";
	}
}
