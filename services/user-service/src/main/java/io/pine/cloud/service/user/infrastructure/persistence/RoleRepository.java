package io.pine.cloud.service.user.infrastructure.persistence;

import io.pine.cloud.service.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Role仓库
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/2/5
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * 按名称查找
     *
     * @param name
     * @return
     */
    Role findByName(String name);
}
