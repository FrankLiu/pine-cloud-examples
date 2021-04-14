package io.pine.cloud.service.user.domain.jcasbin;

/**
 * Class Description to be replaced
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/4/12
 */
public interface Effect {
    /**
     * 是否匹配
     *
     * @param request
     * @param policy
     * @return
     */
    boolean isEffect(Request request, Policy policy);
}
