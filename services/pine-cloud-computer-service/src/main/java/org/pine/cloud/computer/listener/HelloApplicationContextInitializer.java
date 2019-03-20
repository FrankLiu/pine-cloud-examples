package org.pine.cloud.computer.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("运行Initializer:ApplicationContextInitializer...initialize..." + configurableApplicationContext.getApplicationName());
    }
}
