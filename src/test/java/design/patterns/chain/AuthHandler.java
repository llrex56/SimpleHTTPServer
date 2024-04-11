package design.patterns.chain;

import java.util.Optional;

public class AuthHandler extends Handler<Member, Void> {

    @Override
    public Void exec(Member member) {
        if (!"管理员".equals(member.getRoleName())) {
            System.out.println("您不是管理员，没有操作权限");
            return null;
        }
        System.out.println("您是管理员，允许操作");

        return Optional.ofNullable(next).map(__ -> next.exec(member)).orElse(null);
    }
}