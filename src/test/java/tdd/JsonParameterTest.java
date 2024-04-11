package tdd;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/4/24 15:43
 */
public class JsonParameterTest {

    private static final String RESOURCE_PATH = "C:\\Users\\llrex\\workspace\\SimpleHTTPServer\\src\\test\\java\\tdd\\";

    @Test
    public void test() {
        System.out.println(getClass().getPackage());
        System.out.println(getClass().getResource(""));
        System.out.println(System.getProperty("user.dir"));

        final String s = ResourceUtil.readStr(RESOURCE_PATH + "tdd/person-list.json", Charset.defaultCharset());
        //final String ss = ResourceUtil.readStr(getClass().getResource("person-list.json").getPath(), Charset.defaultCharset());
        final List<Person> people = JSONUtil.toList(s, Person.class);

        System.out.println(people);
    }
}
