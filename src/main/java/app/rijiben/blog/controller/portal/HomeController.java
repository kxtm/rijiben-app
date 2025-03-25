package app.rijiben.blog.controller.portal;

import app.rijiben.blog.common.base.IController;
import app.rijiben.blog.service.IUserFace;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //throw new RuntimeException("测试异常");
        return "portal/index";
    }

    @GetMapping("/test")
    @ResponseBody
    public Map<String, Object>  test(HttpServletRequest req) {
        Map<String, Object> model = new HashMap<>();
        model.put("hello", "test");
        return model;
    }
}
