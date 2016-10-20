package cat.prv.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "Welcome to Provisioning Gateway</div><br><br>";
		return new ModelAndView("index", "message", message);
	}
}
