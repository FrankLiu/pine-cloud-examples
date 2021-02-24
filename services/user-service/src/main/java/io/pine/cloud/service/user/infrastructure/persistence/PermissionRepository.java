package io.pine.cloud.service.user.infrastructure.persistence;

import io.pine.cloud.service.user.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Permission仓库
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/2/5
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    /**
     * 按名称查找
     *
     * @param name
     * @return
     */
    Permission findByName(String name);
}
