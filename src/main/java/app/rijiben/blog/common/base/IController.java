package app.rijiben.blog.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class IController {
    protected Logger log = LoggerFactory.getLogger(getClass());

    public String getIp() {
        log.trace("getIp()");
        return "";
    }
}
