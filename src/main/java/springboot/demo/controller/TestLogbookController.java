package springboot.demo.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 测试logbook日志输出
 */
@RestController
@RequestMapping("/logbook")
@RequiredArgsConstructor
public class TestLogbookController {

    @GetMapping("/test/get")
    @ResponseBody
    public Object testGet(@RequestParam String param) {
        return param;
    }

    @PostMapping("/test/post")
    @ResponseBody
    public Object testPost(@RequestParam String param) {
        return param;
    }

    @PostMapping("/test/json")
    @ResponseBody
    public Object testJson(@RequestBody TestJsonParam param) {
        return param;
    }

    @Data
    static class TestJsonParam {
        private String id;
        private String name;
    }
}