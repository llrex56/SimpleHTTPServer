package springboot.demo.controller;

import com.github.davidmarquis.redisscheduler.RedisTaskScheduler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.demo.domain.IntAuthToken;
import springboot.demo.domain.Student;
import springboot.demo.service.StudentService;
import springboot.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class SimpleHttpController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "{version}/test-api", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> testApi(@PathVariable String version) {
        Map<String, Object> result = new HashMap<>();
        result.put("ret", 1000);
        return result;
    }

    @RequestMapping(value = "{version}/{action}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> arithmetic(@PathVariable String version, @PathVariable String action,
                                          @RequestParam(required = true) Integer a,
                                          @RequestParam(required = true) Integer b) {
        Map<String, Object> result = new HashMap<>();
        result.put("ret", 1000);
        result.put("action", action);
        result.put("result", a + b);
        result.put("version", version.replace("v", ""));
        return result;
    }


    @RequestMapping(value = "{version}/{action}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> arithmeticPost(@PathVariable String version, @PathVariable String action,
                                          @RequestParam(required = true) Integer a,
                                          @RequestParam(required = true) Integer b) {
        Map<String, Object> result = new HashMap<>();
        result.put("ret", 1000);
        result.put("result", a + b);
        result.put("action", action);
        result.put("version", version.replace("v", ""));
        return result;
    }

    @RequestMapping(value = "/tutorial/student/list",method = {RequestMethod.GET})
    public String studentList(Model model) {
        List<Student> students = studentService.listAll();
        model.addAttribute("students", students);
        return "student_list";
    }


    @RequestMapping(value = "/user/self/name",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> username(String intAuthToken) {
        Map<String, Object> result = new HashMap<>();

        IntAuthToken iat = userService.findByIntAuthToken(intAuthToken);

        if (iat == null) {
            result.put("ret", 1001);
            return result;
        }
        result.put("name", iat.getUser().getName());
        result.put("userId", iat.getUser().getId());
        result.put("greeting", String.format("Welcome '%s' user from '%s'!", iat.getDevice(), iat.getIp()));
        return result;
    }
}