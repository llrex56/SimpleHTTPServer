package springboot.demo.service

import spock.lang.Specification
import spock.lang.Unroll

class StudentServiceSpec extends Specification {



    @Unroll
    def "maximum of two numbers"() {
        expect:
        Math.max(a as double, b as double) == c

        where:
        a << [3, 5, 9]
        b << [7, 4, 9]
        c << [7, 5, 9]
    }


}
