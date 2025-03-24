package app.rijiben.blog.controller.portal;

import app.rijiben.blog.common.base.IController;
import app.rijiben.blog.service.IUserFace;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends IController {

    final IUserFace userFace;

    @Autowired
    public HomeController(IUserFace userFace) {
        this.userFace = userFace;
    }

    @GetMapping("/")
    public String home(HttpServletRequest req) {
        userFace.getUser("11");
        req.setAttribute("hello", "111");
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            list.add(String.valueOf(i));
        }

        req.setAttribute("list", list);

        return "portal/index";
    }

}
