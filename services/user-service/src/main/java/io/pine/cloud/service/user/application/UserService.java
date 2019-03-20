package io.pine.cloud.service.user.application;

import io.pine.cloud.service.user.domain.User;
import io.pine.cloud.service.user.domain.UserCreatedEvent;
import io.pine.cloud.service.user.domain.UserDeletedEvent;
import io.pine.cloud.service.user.domain.UserUpdatedEvent;
import io.pine.cloud.service.user.infrastructure.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 查询所有用户
     *
     * @return 所有用户列表
     */
    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * 根据用户名模糊查找所有用户
     *
     * @param name 用户名
     * @return 用户列表
     */
    public List<User> findUserByName(String name) {
        logger.info("根据用户名模糊查找所有用户: {}", name);
        return userRepository.findByNameLike(name);
    }

    /**
     * 根据用户id查找用户
     *
     * @param userId 用户ID
     * @return 用户实体或空对象
     */
    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? user.get(): null;
    }

    /**
     * 创建新的用户
     *
     * @param user 用户
     * @return 新创建的用户实体
     */
    @Transactional
    public User createUser(User user) {
        logger.info("创建新的用户: {}", user);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setDelFlag(false);
        userRepository.save(user);

        // 发送事件
        eventPublisher.publishEvent(new UserCreatedEvent(user));

        return user;
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 更新后的用户实体
     */
    @Transactional
    public User updateUser(User user) {
        logger.info("更新用户: {}", user);
        userRepository.save(user);

        // 发送事件
        eventPublisher.publishEvent(new UserUpdatedEvent(user));
        // 返回结果
        return user;
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 已删除的用户ID
     */
    @Transactional
    public User deleteById(Long userId) {
        logger.info("删除用户: {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            logger.warn("用户不存在!");
            return null;
        }
        User user = optionalUser.get();
        user.setDelFlag(true);
        userRepository.save(user);

        // 发送事件
        eventPublisher.publishEvent(new UserDeletedEvent(user));

        // 返回结果
        return user;
    }
}
