package org.pine.cloud.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * based on spring2
 */
@Aspect
public class Log2BeforeLogin {

    @Pointcut("execution(* org.pine.cloud.spring.aop.*.login(..))")
    public void loginMethod(){}

    @Before("loginMethod()")
    public void beforeLogin(){
        System.out.println("有人要登录了2。。。");
    }

    @After("loginMethod()")
    public void afterLogin() {
        System.out.println("有人登陆成功了2");
    }
}
