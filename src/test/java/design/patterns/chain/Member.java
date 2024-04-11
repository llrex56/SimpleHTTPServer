package design.patterns.chain;

import lombok.Data;

@Data
public class Member {

    private String loginName;
    private String loginPass;
    private String roleName;

    public Member(String loginName, String loginPass) {
        this.loginName = loginName;
        this.loginPass = loginPass;
    }
}

