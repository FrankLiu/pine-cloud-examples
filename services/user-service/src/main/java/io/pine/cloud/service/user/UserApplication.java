package io.pine.cloud.service.user;

import org.casbin.jcasbin.main.Enforcer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserApplication.class, args);

        test_casbin();
    }

    public static void test_casbin() throws IOException {
        Resource modelUri = new ClassPathResource("/casbin/model.conf");
        Resource policyUri = new ClassPathResource("/casbin/policy.csv");
        Enforcer e = new Enforcer(modelUri.getFile().getPath(), policyUri.getFile().getPath());

        // the user that wants to access a resource.
        String sub = "wenyin";
        // the resource that is going to be accessed.
        String obj = "project";
        // the operation that the user performs on the resource.
        String act = "read";
        List<String> roles = e.getRolesForUser("wenyin");
        System.out.println("roles: " + roles.stream().collect(Collectors.joining(",")));

        if (e.enforce(sub, obj, act) == true) {
            System.out.println("permit wenyin to read project");
        } else {
            System.out.println("deny the request, show an error");
        }
    }
}
