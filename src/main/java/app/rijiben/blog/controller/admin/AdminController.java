package app.rijiben.blog.controller.admin;

import app.rijiben.blog.common.consts.Constr;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gl")
public class AdminController {

    @GetMapping({"/",""})
    public ModelAndView index() {

        return new ModelAndView(Constr.ADMIN_VIEW.concat("index"));
    }

    @PostMapping("/login")
    public ModelAndView login() {
        return new ModelAndView(Constr.ADMIN_VIEW.concat("index"));
    }
    //captcha
    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView(Constr.ADM_URL);
    }

    @GetMapping("/dash")
    public ModelAndView dash(HttpServletRequest req) {
        return new ModelAndView(Constr.ADMIN_VIEW.concat("dash"));
    }

}
