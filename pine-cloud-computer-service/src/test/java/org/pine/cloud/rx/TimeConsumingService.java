package org.pine.cloud.rx;

import java.util.concurrent.Callable;

/**
 * @author liujun
 * @sinace 2017/11/15 0015.
 */
public class TimeConsumingService implements Callable<String> {
    private String serviceName;
    private int waitMs;

    public TimeConsumingService(String serviceName, int waitMs, String[] depandencies){
        this.serviceName = serviceName;
        this.waitMs = waitMs;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(this.waitMs);
        return String.format("service %s exec time is: %d", this.serviceName, this.waitMs);
    }
}
