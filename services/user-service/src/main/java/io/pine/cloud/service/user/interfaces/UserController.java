package io.pine.cloud.service.user.interfaces;

import io.pine.cloud.service.user.application.UserService;
import io.pine.cloud.service.user.domain.User;
import io.pine.cloud.service.user.interfaces.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="创建用户", notes="根据User对象创建用户", produces = "application/json")
    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User newUser(@ModelAttribute(name = "user") UserVo userVo) {
        User user = new User();
        user.setName(userVo.getUsername());
        user.setPassword(userVo.getPasswd());
        user.setAge(userVo.getAge());
        user.setEmail(userVo.getEmail());
        return userService.createUser(user);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", produces = "application/json")
    @ResponseBody
    @RequestMapping(value = "/users", method=RequestMethod.GET)
    public Page<User> getUsers(Pageable pageable){
        return userService.findUsers(pageable);
    }
}
