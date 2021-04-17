package io.pine.cloud.service.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class DeletableEntity extends BaseEntity{
    @JsonIgnore
    @Column(name = "del_flag", columnDefinition = "bit not null default 0 comment '删除标识（1 已删除 0 未删除）'")
    private Boolean delFlag;

    protected DeletableEntity() {
        setDelFlag(false);
    }
}
