package io.pine.cloud.user.infrastructure;

import io.pine.cloud.user.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Repository
 *
 * @author liujun
 * @sinace 2017/11/30 0030.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

    List<User> findAll(Specification<User> spec);
}
