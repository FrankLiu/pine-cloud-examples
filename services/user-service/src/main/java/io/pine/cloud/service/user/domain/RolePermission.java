package io.pine.cloud.service.user.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 角色权限关系
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
public class RolePermission {
    @Column(name = "name", length = 100, nullable = false)
    private String permission;


}
