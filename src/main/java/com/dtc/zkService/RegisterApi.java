package com.dtc.zkService;

import com.google.common.collect.Lists;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RegisterApi {

    public static List<Method> registerApi(UserContext userContext, Class aClass) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(aClass);

        Method[] methods = aClass.getDeclaredMethods();
        userContext.addConsumer(aClass);
        List<Method> methedList = Lists.newArrayList(methods);
        return methedList;
    }
}
