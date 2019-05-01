package org.pine.cloud.spring.aop;

public class LoginServiceImpl implements LoginService{

    @Override
    public String login(String loginName, String password) {
        System.out.printf("User [%s] is login...", loginName);
        return "success";
    }
}
