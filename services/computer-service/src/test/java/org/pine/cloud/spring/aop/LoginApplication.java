package org.pine.cloud.spring.aop;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;


public class LoginApplication {
    public static void main(String... args) {
        proxyByDefault();
        proxyByAutoProxy();
    }

    public static void proxyByDefault() {
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(new LoginServiceImpl());
        factory.addAdvice(new LogBeforeLogin());

        LoginService loginService = (LoginService) factory.getProxy();
        loginService.login("tester", "123456");
    }

    public static void proxyByAutoProxy() {
        AspectJProxyFactory factory = new AspectJProxyFactory();//创建代理工厂
        factory.setTarget(new LoginServiceImpl());//设置目标类
        factory.addAspect(Log2BeforeLogin.class);//设置增强

        LoginService loginService = factory.getProxy();//获取代理
        loginService.login("tester", "23456");
    }
}
