package com.dtc.zkService;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;
import com.dtc.bean.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class DefaultUserContext implements UserContext {

    private final ApplicationContext applicationContext;

    private final User user;

    private ApplicationConfig defaultApplicationConfig;


    public DefaultUserContext(User user) {
        applicationContext = new GenericApplicationContext();
        this.user=user;
    }

    void init() {
        defaultApplicationConfig = new ApplicationConfig();
        defaultApplicationConfig.setLogger("slf4j");
        defaultApplicationConfig.setName(user.getUserName());
        defaultApplicationConfig.setDefault(Boolean.TRUE);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setPort(20888);
        registryConfig.setProtocol("zookeeper");
        defaultApplicationConfig.setRegistry(registryConfig);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public ReferenceBean addConsumer(Class aClass) {

        ReferenceBean referenceBean = new ReferenceBean();
        referenceBean.setApplicationContext(applicationContext);
        referenceBean.setApplication(defaultApplicationConfig);

        return referenceBean;
    }

    @Override
    public ServiceBean addProvider(Class aClass) {
        return null;
    }
}
