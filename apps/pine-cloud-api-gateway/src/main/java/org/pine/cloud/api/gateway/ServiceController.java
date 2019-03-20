package org.pine.cloud.api.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank
 * @sinace 2018/12/20 0020.
 */
@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    private String serviceId = "computer-service";

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances(serviceId);
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose(serviceId).getUri().toString();
    }

    @RequestMapping("/call")
    public String call() {
        ServiceInstance serviceInstance = loadBalancer.choose(serviceId);
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("a", 5);
        params.put("b", 3);
        Integer callServiceResult = new RestTemplate().getForObject( "http://192.168.2.211:2223/add?a={a}&b={b}", Integer.class, params);
//        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri() + "/cal", String.class);
        System.out.println(callServiceResult);
        return String.valueOf(callServiceResult);
    }
}
