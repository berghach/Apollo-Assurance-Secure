package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomepageController {

    @GetMapping
    public ModelAndView homepage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
