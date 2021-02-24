package io.pine.cloud.service.user.domain;

import com.google.common.base.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色模型
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/2/5
 */
@Data
@Entity
@Table(name = "p_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Role extends DeletableEntity {
    @Column(name = "name", unique = true, length = 30, columnDefinition = "varchar(30) not null default '' comment '角色名称'")
    private String name;

    @Column(columnDefinition = "varchar(100) not null default '' comment '角色描述'")
    private String description;

    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "p_role_permission",
            uniqueConstraints = @UniqueConstraint(columnNames = {"role_name", "permission_name"}),
            joinColumns = {
                    @JoinColumn(name = "role_name", referencedColumnName = "name"),
                    @JoinColumn(name = "permission", referencedColumnName = "name")
            }
    )
    private Set<Permission> permissions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true ;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role that = (Role) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

