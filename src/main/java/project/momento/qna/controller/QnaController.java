package project.momento.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.menu.service.MenuService;
import project.momento.page.Criteria;

@Controller
public class QnaController {

	/*
	 * @Autowired private SignService SignService;
	 */

	@Autowired
	private MenuService menuService;
	@Autowired
	private LoginService loginService;

	/*
	 * 사이트에서 이동
	 *
	 */

}
