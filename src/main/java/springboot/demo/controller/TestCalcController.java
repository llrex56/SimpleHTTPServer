package springboot.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2020/10/12 9:39
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestCalcController {

    @GetMapping("/test/calc")
    @ResponseBody
    public Object testCalc(MetricType type, Integer a, Integer b) {

        Integer apply = type.apply(a, b);

        return apply;
    }


    interface MetricCalculator extends BiFunction<Integer, Integer, Integer> {
    }

    @Component
    @RequiredArgsConstructor
    static class MetricFactory {

        private final List<MetricCalculator> metricsCalculators;

        private static Map<Class<?>, MetricCalculator> metricsCalculatorMapsHolder;

        @PostConstruct
        public void init() {
            metricsCalculatorMapsHolder = metricsCalculators.stream().collect(Collectors.toMap(AopUtils::getTargetClass, Function.identity()));
        }

        public static MetricCalculator get(Class<? extends MetricCalculator> clazz) {
            return metricsCalculatorMapsHolder.get(clazz);
        }
    }

    @AllArgsConstructor
    @Getter
    enum MetricType implements MetricCalculator {
        ADD(Add.class),
        SUB(Sub.class),
        ;

        Class<? extends MetricCalculator> clazz;

        @Override
        public Integer apply(Integer x, Integer y) {
            return MetricFactory.get(this.clazz).apply(x, y);
        }
    }

    @Component
    @RequiredArgsConstructor
    static class Add implements MetricCalculator {

        @Override
        public Integer apply(Integer x, Integer y) {
            return x + y;
        }
    }

    @Component
    @RequiredArgsConstructor
    static class Sub implements MetricCalculator {

        @Override
        public Integer apply(Integer x, Integer y) {
            return x - y;
        }
    }
}
