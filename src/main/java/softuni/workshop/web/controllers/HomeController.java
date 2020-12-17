package softuni.workshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = super.view("home");
        boolean areImported = false;
        modelAndView.addObject("areImported",areImported);
        return modelAndView;
    }
}
