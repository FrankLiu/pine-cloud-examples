package io.pine.cloud.service.user.domain;

import com.google.common.base.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 权限模型
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/2/5
 */
@Data
@Entity
@Table(name = "p_permission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Permission extends DeletableEntity {
    @Column(name = "name", columnDefinition = "varchar(100) not null default '' comment '权限名称'")
    private String name;

    @Column(columnDefinition = "varchar(255) not null default '' comment '权限描述'")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true ;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Permission that = (Permission) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
