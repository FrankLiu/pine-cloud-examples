package io.pine.cloud.service.user.domain.jcasbin;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基于casbin实现的PERM模型
 *
 * [request_definition]
 * r = sub, obj, act
 *
 * 分别表示 request 中的
 * accessing entity (Subject)
 * accessed resource (Object)
 * the access method (Action)
 *
 * [policy_definition]
 * p = sub, obj, act
 * p2 = sub, act
 *
 * 定义的每一行称为 policy rule, p, p2 是 policy rule 的名字. p2 定义的是 sub 所有的资源都能执行 act
 *
 * [policy_effect]
 * e = some(where (p.eft == allow))
 *
 * 上面表示有任意一条 policy rule 满足, 则最终结果为 allow
 *
 * [matchers]
 * m = r.sub == p.sub && r.obj == p.obj && r.act == p.act
 *
 * 定义了 request 和 policy 匹配的方式, p.eft 是 allow 还是 deny, 就是基于此来决定的
 *
 * [role_definition]
 * g = _, _
 * g2 = _, _
 * g3 = _, _, _
 *
 * g, g2, g3 表示不同的 RBAC 体系, _, _ 表示用户和角色 _, _, _ 表示用户, 角色, 域(也就是租户)
 *
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/4/12
 */
@Data
@Accessors(chain = true)
public class Request {
    /** accessing entity (Subject) */
    private String sub;
    /** accessed resource (Object) */
    private String obj;
    /** the access method (Action) */
    private String act;
}
