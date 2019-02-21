package io.pine.cloud.auth.domain;

import javax.persistence.*;

/**
 * Entity - User
 *
 * @author liujun
 * @sinace 2017/11/30 0030.
 */
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String mobile;

    @Column(nullable = false)
    private Integer age;

    public User(String name, Integer age){
        this.setName(name);
        this.setAge(age);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true; }
        if (!(o instanceof User)){ return false; }

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null){ return false; }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null){ return false; }
        return getMobile() != null ? getMobile().equals(user.getMobile()) : user.getMobile() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        return result;
    }
}
