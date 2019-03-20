package io.pine.cloud.service.user.infrastructure.persistence;

import io.pine.cloud.service.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 依据用户名模糊查找
     *
     * @param name 用户名
     * @return
     */
    List<User> findByNameLike(String name);


}
