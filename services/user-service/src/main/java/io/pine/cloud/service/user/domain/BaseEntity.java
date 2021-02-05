package io.pine.cloud.service.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @CreatedBy
    @Column(columnDefinition = "varchar(64) comment '记录创建者'")
    private String createdBy;

    @CreatedDate
    @Column(columnDefinition = "datetime not null default CURRENT_TIMESTAMP comment '记录创建时间'")
    private Date createdAt;

    @JsonIgnore
    @LastModifiedBy
    @Column(columnDefinition = "varchar(64) comment '记录最后更新者'")
    private String updatedBy;

    @LastModifiedDate
    @Column(columnDefinition = "datetime not null default CURRENT_TIMESTAMP comment '记录最后更新时间'")
    private Date updatedAt;
}
