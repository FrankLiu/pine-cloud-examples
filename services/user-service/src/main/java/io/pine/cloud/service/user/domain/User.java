package io.pine.cloud.service.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_info")
public class User extends DeletableEntity{
    @Column(name = "username", columnDefinition = "varchar(30) not null comment '用户名'")
    private String name;

    @JsonIgnore
    @Column(name = "password", columnDefinition = "varchar(64) not null comment '用户密码'")
    private String password;

    @Column(columnDefinition = "smallint comment '用户年龄'")
    private int age;

    @Column(columnDefinition = "varchar(32) comment '用户邮箱'")
    private String email;
}
