package io.pine.cloud.service.user.interfaces.vo;

import lombok.Data;

@Data
public class UserVo {
    private String username;

    private String passwd;

    private String email;

    private int age;
}
