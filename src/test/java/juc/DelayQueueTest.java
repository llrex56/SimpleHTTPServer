package juc;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/2/10 11:50
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayEntity> delayQueue = new DelayQueue<>();

        for (int i = 1; i <= 10; i++) {
            delayQueue.offer(DelayEntity.of(i, 1, "task-" + i, i * 10_000));
        }

        while (true) {
            if (!delayQueue.isEmpty()) {
                DelayEntity take = delayQueue.take();
                System.out.println(take);
            }
        }
    }
}

@Data
class DelayEntity implements Delayed {
    /**
     * 任务id
     */
    private int id;
    /**
     * 业务类型
     */
    private int type;
    /**
     * 业务数据
     */
    private String data;
    /**
     * 执行时间
     */
    private long executeTime;

    public static DelayEntity of(int id, int type, String data, long executeTime) {
        return new DelayEntity(){{
            setId(id);
            setType(type);
            setData(data);
            setExecuteTime(TimeUnit.NANOSECONDS.convert(executeTime, TimeUnit.MILLISECONDS) + System.nanoTime());
        }};
    }

    /**
     * Returns the remaining delay associated with this object, in the
     * given time unit.
     *
     * @param unit the time unit
     * @return the remaining delay; zero or negative values indicate
     * that the delay has already elapsed
     */
    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NotNull Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}
