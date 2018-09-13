package org.pine.cloud.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ConstraintMode;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author liujun
 * @sinace 2017/12/1 0001.
 */
public class Living {
    Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "actorId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    public Actor actor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "regionId", foreignKey = @ForeignKey(name = "none", value =ConstraintMode.NO_CONSTRAINT))
    public Region region;
}
