package project.momento.testjsp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspTestController {
	
	 @RequestMapping(value = "/jspindex")
	    public String index(){
	        return "index";
	    }
	 @RequestMapping("/jspTest")
	    public ModelAndView test() throws Exception{
	        ModelAndView mav = new ModelAndView("test");
	        mav.addObject("name", "goddaehee");

	        List<String> testList = new ArrayList<String>();
	        testList.add("a");
	        testList.add("b");
	        testList.add("c");

	        mav.addObject("list", testList);
	        return mav;
	    }
}
