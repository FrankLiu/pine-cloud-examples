package io.pine.cloud.service.user.infrastructure.persistence;

import io.pine.cloud.service.user.domain.Role;
import io.pine.cloud.service.user.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Description to be replaced
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/4/17
 */
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;

    @Test
    public void test_findByName() {
        Role role = new Role("role1");
        roleRepository.save(role);

        User user = User.of("tester1", "pwd1", 20, "tester1@163.com");
        user.getRoles().add(role);
        userRepository.save(user);

        List<User> result = userRepository.findByNameLike("tester1");
        Assert.assertNotNull(result);
        Assert.assertEquals("tester1", result.get(0).getName());
        List<Role> userRoles = result.get(0).getRoles().stream().collect(Collectors.toList());
        Assert.assertEquals(1, userRoles.size());
        Assert.assertEquals("role1", userRoles.get(0).getName());
    }
}
