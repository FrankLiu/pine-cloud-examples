package io.pine.cloud.auth.infrastructure;


import io.pine.cloud.auth.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 * @sinace 2017/12/1 0001.
 */
public class PageableParam {
    private Integer pageSize = 10;
    private Integer pageNumber = 1;
    private String searchName;
    private String searchMobile;
    private String searchId;

    public PageableParam(){}

    public Specification<User> buildSpec(){
        Specification<User> specification = new Specification<User>() {

            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<javax.persistence.criteria.Predicate> list = new ArrayList<>();

                if (!StringUtils.isEmpty(searchName)) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + searchName + "%"));
                }

                if (!StringUtils.isEmpty(searchId)) {
                    list.add(cb.equal(root.get("id").as(Long.class), searchId));
                }

                if (!StringUtils.isEmpty(searchMobile)) {
                    list.add(cb.like(root.get("mobile").as(String.class), "%" + searchMobile + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            };
        };
        return specification;
    }
}
