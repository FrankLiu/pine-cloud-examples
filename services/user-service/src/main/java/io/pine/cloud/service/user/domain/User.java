package io.pine.cloud.service.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "p_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends DeletableEntity {
    @Column(name = "username", unique = true, length = 30, nullable = false, columnDefinition = "varchar(30) not null default '' comment '用户名'")
    private String name;

    @JsonIgnore
    @Column(name = "password", length = 64, nullable = false, columnDefinition = "varchar(64) not null default '' comment '用户密码'")
    private String password;

    @Column(columnDefinition = "smallint comment '用户年龄'")
    private int age;

    @Column(columnDefinition = "varchar(32) comment '用户邮箱'")
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "p_user_role",
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_name", "role_name"}),
            joinColumns = {
                    @JoinColumn(name = "user_name", referencedColumnName = "username"),
                    @JoinColumn(name = "role_name", referencedColumnName = "name")
            }
    )
    private Set<UserRole> userRoles = new HashSet<>();

    private User(String name, String password, int age, String email) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public static User of(String name, String password, int age, String email) {
        return new User(name, password, age, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true ;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

}

