package app.rijiben.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gl")
public class AdminController {

    @GetMapping("/")
    public ModelAndView index() {

        return new ModelAndView("admin/index");
    }

}
