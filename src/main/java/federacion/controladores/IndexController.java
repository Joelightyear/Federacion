package federacion.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping (method = RequestMethod.GET)
	public String contacto (Model model){
		return "index";
	}
	
	

}

