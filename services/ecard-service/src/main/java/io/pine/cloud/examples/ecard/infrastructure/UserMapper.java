package io.pine.cloud.examples.ecard.infrastructure;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.pine.cloud.examples.ecard.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("select id,name,age,email from user")
    List<User> findAll();
}
