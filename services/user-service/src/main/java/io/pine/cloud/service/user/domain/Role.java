package io.pine.cloud.service.user.domain;

import com.google.common.base.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "name", unique = true, nullable = false, length = 30, columnDefinition = "varchar(30) not null default '' comment '角色名称'")
    private String name;

    @Column(length = 100, nullable = true, columnDefinition = "varchar(100) default '' comment '角色描述'")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Permission.class)
    @JoinTable(
            name = "p_role_permission",
            uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permission_id"}),
            joinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "permission_id", referencedColumnName = "id")
            }
    )
    private Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        setName(name);
    }

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

