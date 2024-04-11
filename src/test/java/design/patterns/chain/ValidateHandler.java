package design.patterns.chain;

import org.springframework.util.StringUtils;

import java.util.Optional;

public class ValidateHandler extends Handler<Member, Void> {

    @Override
    public Void exec(Member member) {
        if (StringUtils.isEmpty(member.getLoginName()) || StringUtils.isEmpty(member.getLoginPass())) {
            System.out.println("用户名或者密码为空");
            return null;
        }
        System.out.println("用户名和密码校验成功，可以往下执行");
        return Optional.ofNullable(next).map(__ -> next.exec(member)).orElse(null);
    }
}

