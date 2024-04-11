package queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author luozhenhong
 * @version 1.0
 * 2022/5/10 19:42
 */
public class PriorityQueueTest {

    public static void main(String[] args) {

        integerTest();

        personTest();
    }

    private static void personTest() {
        Queue<Person> persons = new PriorityQueue<>((a, b) -> {
            int cmp = a.getBirthday().compareTo(b.getBirthday());

            if (cmp == 0) {
                return a.getId().compareTo(b.getId());
            }
            return cmp;
        });

        persons.add(Person.of(LocalDateTime.of(1992, 1, 2, 0, 0, 0, 0), "zs", 3));
        persons.add(Person.of(LocalDateTime.of(1993, 1, 2, 0, 0, 0, 0), "ls", 2));
        persons.add(Person.of(LocalDateTime.of(1994, 1, 2, 0, 0, 0, 0), "ww", 1));
        persons.add(Person.of(LocalDateTime.of(1996, 1, 2, 0, 0, 0, 0), "zl", 4));
        persons.add(Person.of(LocalDateTime.of(1992, 1, 2, 0, 0, 0, 0), "xm", 5));
        persons.add(Person.of(LocalDateTime.of(1993, 1, 2, 0, 0, 0, 0), "xw", 19));
        persons.add(Person.of(LocalDateTime.of(1992, 1, 2, 0, 0, 0, 0), "zh", 17));

        while (!persons.isEmpty()) {
            System.out.printf("%s \n", persons.poll());
        }
        System.out.println();
    }

    private static void integerTest() {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        queue.add(3);
        queue.add(11);
        queue.add(1);
        queue.add(2);
        queue.add(7);
        queue.add(10);
        queue.add(2);
        queue.add(1);

        System.out.println(queue);

        for (Integer item : queue) {
            System.out.printf("%d, ", item);
        }
        System.out.println();

        while (!queue.isEmpty()) {
            System.out.printf("%d, ", queue.poll());
        }
        System.out.println();
    }
}
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
class Person {
    private LocalDateTime birthday;
    private String name;
    private Integer id;
}
