package io.pine.cloud.service.user.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 用户角色关系
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/2/6
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRole {
    @Column(name = "role_name", unique = true, length = 30, columnDefinition = "varchar(30) not null default '' comment '角色名称'")
    private String roleName;

}
