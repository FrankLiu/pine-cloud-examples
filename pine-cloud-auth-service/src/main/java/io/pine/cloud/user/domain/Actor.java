package io.pine.cloud.user.domain;

import javax.persistence.*;
import javax.persistence.ForeignKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @sinace 2017/12/1 0001.
 */
public class Actor {
    Long id;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "actorId")
    List<Living> livings = new ArrayList<>();

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "userDetailId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    UserDetail userDetail;

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    ActorType actorType = ActorType.A;

    public enum ActorType{
        A,B,C
    }
}
