package springboot.demo.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;

/**
 * 测试logbook日志输出
 */
//@Controller
@RestController
@RequestMapping("/logbook")
@RequiredArgsConstructor
public class TestLogbookController {

    @GetMapping("/test/get")
    @ResponseBody
    public Object testGet(@RequestParam String param, HttpServletRequest request) {
        Locale locale = request.getLocale();
        Enumeration<Locale> locales = request.getLocales();
        return param;
    }

//    @RequestMapping("/test/post")
    @PostMapping("/test/post")
    @ResponseBody
    public Object testPost(TestJsonParam param, @RequestParam String param2, String param3) {
        return param + " : " + param2 + " : " + param3;
    }

    @PostMapping("/test/json")
    @ResponseBody
    public Object testJson(@RequestBody TestJsonParam param) {
        return param;
    }

    @Data
    public static class TestJsonParam {
        private String id;
        private String name;

        private User user;
    }

    @Data
    public static class User {
        private String id;
        private String userName;
    }
}