package top.b0x0.boot.chatroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author TANG
 * @since 2021-07-28
 * @since JDK1.8
 */
@Controller
public class UserController {

    /**
     * http://localhost:8080/Chatrum/test
     *
     * @return /
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String echo() {
        return Thread.currentThread().getName();
    }

}
