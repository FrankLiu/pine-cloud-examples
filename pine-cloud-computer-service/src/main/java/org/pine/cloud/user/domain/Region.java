package org.pine.cloud.user.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author liujun
 * @sinace 2017/12/1 0001.
 */
public class Region {
    Long id;

    String name;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId")
    List<Living> Livings;
}
