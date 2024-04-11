package design.patterns.chain;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/8/17 9:20
 */
public class ChainClient {

    public static void main(String[] args) {
        final Handler<Member, Void> loginHandler = new Handler.Builder<Member, Void>()
                .addHandler(new ValidateHandler())
                .addHandler(new LoginHandler())
                .addHandler(new AuthHandler())
                .build();

        loginHandler.exec(new Member("zs", "admin"));
    }
}
