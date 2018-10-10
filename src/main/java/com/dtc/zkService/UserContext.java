package com.dtc.zkService;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.dtc.bean.User;
import org.springframework.beans.factory.BeanFactory;

import java.util.function.Consumer;

public interface UserContext {
    User getUser();
    ReferenceBean addConsumer(Class aClass);

    ServiceBean   addProvider(Class aClass);


}
