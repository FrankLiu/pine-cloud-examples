package io.pine.cloud.user.domain;

import javax.persistence.*;

/**
 * @author liujun
 * @sinace 2017/12/1 0001.
 */
public class UserDetail {
    Long id;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "actorId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    Actor actor;

    String truename;
}
