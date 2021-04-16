package io.pine.cloud.service.user;

import io.pine.cloud.service.user.domain.jcasbin.Policy;
import org.casbin.adapter.JdbcAdapter;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.spring.boot.autoconfigure.properties.CasbinExceptionProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Class Description to be replaced
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/4/14
 */
public class EnforcerFactory implements InitializingBean {
    private static Enforcer enforcer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        //从数据库读取策略
        CasbinExceptionProperties exceptionProperties = new CasbinExceptionProperties();
        JdbcAdapter jdbcAdapter = new JdbcAdapter(jdbcTemplate, exceptionProperties, true);

        enforcer = new Enforcer("classpath:/casbin/model.conf", jdbcAdapter);
        enforcer.loadPolicy();//Load the policy from DB.
    }

    /**
     * 添加权限
     * @param policy
     * @return
     */
    public static boolean addPolicy(Policy policy){
        boolean addPolicy = enforcer.addPolicy(policy.getSub(),policy.getObj(),policy.getAct());
        enforcer.savePolicy();

        return addPolicy;
    }

    /**
     * 删除权限
     * @param policy
     * @return
     */
    public static boolean removePolicy(Policy policy){
        boolean removePolicy = enforcer.removePolicy(policy.getSub(),policy.getObj(),policy.getAct());
        enforcer.savePolicy();

        return removePolicy;
    }

    public static Enforcer getEnforcer(){
        return enforcer;
    }
}
