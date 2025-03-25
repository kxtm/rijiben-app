package app.rijiben.blog.common.config;

import app.rijiben.blog.common.consts.Constr;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthConfig implements HandlerInterceptor {
    final static Logger log = LoggerFactory.getLogger(AuthConfig.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("当前请求的地址是{}", request.getRequestURI());
        String token = request.getHeader(Constr.LOGIN_TOKEN);
        if (ObjectUtils.isEmpty(token)) {
            response.sendRedirect(request.getContextPath().concat(Constr.ADM_URL));
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
