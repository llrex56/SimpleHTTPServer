package springboot.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2020/10/12 9:39
 */
@RestController
@RequestMapping("/validator")
@RequiredArgsConstructor
public class ValidatorController {

    private final LoginValidators loginValidators;

    @GetMapping("/test")
    @ResponseBody
    public Object test(String password) {
        return loginValidators.isValid(password);
    }

    @Component
    @RequiredArgsConstructor
    static class LoginValidators {

        private final List<Validator<String>> validators;

        public boolean isValid(String password) {
            for (Validator<String> validator : validators) {
                if (!validator.isValid(password)) {
                    return false;
                }
            }
            return true;
        }
    }

    @FunctionalInterface
    interface Validator<INPUT> {

        boolean isValid(INPUT input);
    }

    @Component
    static class PasswordLoginValidator implements Validator<String> {

        @Override
        public boolean isValid(String password) {
            return "password".equals(password);
        }
    }

    @Component
    static class CanLoginValidator implements Validator<String> {

        @Override
        public boolean isValid(String s) {
            // do something
            return true;
        }
    }

}
