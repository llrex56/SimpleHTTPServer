package design.patterns.chain;

import java.util.Optional;

public class LoginHandler extends Handler<Member, Void> {

    @Override
    public Void exec(Member member) {
        System.out.println("登录成功！");
        member.setRoleName("管理员");
        return Optional.ofNullable(next).map(__ -> next.exec(member)).orElse(null);
    }
}