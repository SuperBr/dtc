package com.dtc.zkService;


import com.dtc.bean.User;
import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentHashMap;

public class CentreContext {

    private ConcurrentHashMap<User, UserContext> userContextCache = new ConcurrentHashMap<>();

}
