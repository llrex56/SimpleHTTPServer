package springboot.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2020/10/12 9:40
 */
@RestController
@RequestMapping("/multilevel-cache")
@RequiredArgsConstructor
public class TestMultilevelCacheController {


    @GetMapping("/test/{key}")
    @Cacheable(value = "multilevel-cache", key = "#key")
    public Object test(@PathVariable("key") String key) {
        return "Success";
    }
}