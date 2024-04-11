package recursion;

/**
 * 递归
 * @author luozhenhong
 * @version 1.0
 * 2022/7/4 15:03
 */
public class RecursionTest {

    /*
    斐波那契数列
    1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, …

    f5 = f4 + f3
    f4 = f3 + f2
    f3 = f2 + f1
    f2 = 1
    f1 = 1

    f1 = 1
    f2 = 1
    f3 = f2, 1, 1+1
    f4 = f3, 2, 1+2
    f5 = f4, 3, 2+3
    f6 = f5, 5, 3+5
    f7 = f6, 8, 5+8
     */
    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        System.out.println(recursionTail(1000_00, 1L, 1L)); // 数量太大，会爆栈

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        begin = end;

//        System.out.println(dt(1000_00));//2754320626097736315
        System.out.println(dp(1000_00));//2754320626097736315

        System.out.println(System.currentTimeMillis() - begin);
    }

    /**
     * 递归
     */
    public static long recursion(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return recursion(n - 1) + recursion(n - 2);
    }

    /**
     * 尾递归优化，等同于递推
     *
     * 备注：java不支持尾递归优化
     */
    public static long recursionTail(int n, long a, long b) {
        if (n == 1 || n == 2) {
            return b;
        }
        return recursionTail(n - 1, b, a + b);
    }

    /**
     * 递推
     */
    public static long dt(int n) {
        long a = 1;
        long b = 1;
        long result = 0;

        for (int i = 3; i <= n; i++) {
            result = a + b;

            a = b;
            b = result;
        }
        return result;
    }

    /**
     * 动态规划
     */
    public static long dp(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
