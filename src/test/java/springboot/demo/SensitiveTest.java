package springboot.demo;

import cn.hutool.dfa.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2021/3/29 14:37
 */
public class SensitiveTest {

    public static void main(String[] args) {
        Collection<String> words = new ArrayList<String>(){{
            add("sht");
            add("apple");
            add("mac");
            add("iOS");
            add("iPhone");
            add("mac book");
        }};
        SensitiveUtil.init(words);
        System.out.println(SensitiveUtil.containsSensitive("abc"));
        System.out.println(SensitiveUtil.containsSensitive("apple"));
        System.out.println(SensitiveUtil.containsSensitive("ma   c"));
        System.out.println(SensitiveUtil.containsSensitive("hello world"));
        System.out.println(SensitiveUtil.containsSensitive("i OS"));
        System.out.println(SensitiveUtil.containsSensitive("iPhone 1"));


        System.out.println(SensitiveUtil.getFindedAllSensitive("mac"));
    }
}
